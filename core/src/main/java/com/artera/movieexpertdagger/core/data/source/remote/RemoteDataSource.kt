package com.artera.movieexpertdagger.core.data.source.remote

import android.util.Log
import com.artera.movieexpertdagger.core.data.source.remote.network.ApiResponse
import com.artera.movieexpertdagger.core.data.source.remote.network.ApiService
import com.artera.movieexpertdagger.core.data.source.remote.response.MovieResponse
import com.artera.movieexpertdagger.core.data.source.remote.response.TvResponse
import com.artera.movieexpertdagger.core.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovies(page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getAllMovies(Constant.MOVIES_API, page.toString())
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvs(page: Int): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = apiService.getAllTvs(Constant.MOVIES_API, page.toString())
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

