package com.artera.movieexpertdagger.core.data.source.remote.network

import com.artera.movieexpertdagger.core.data.source.remote.response.MovieRawResponse
import com.artera.movieexpertdagger.core.data.source.remote.response.TvRawResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing?language=en-US")
    suspend fun getAllMovies(
        @Query("api_key") api: String,
        @Query("page") page: String
    ): MovieRawResponse

    @GET("tv/airing_today?language=en-US")
    suspend fun getAllTvs(
        @Query("api_key") api: String,
        @Query("page") page: String
    ): TvRawResponse
}
