package com.artera.movieexpertdagger.core.data

import com.artera.movieexpertdagger.core.data.source.local.LocalDataSource
import com.artera.movieexpertdagger.core.data.source.remote.RemoteDataSource
import com.artera.movieexpertdagger.core.data.source.remote.network.ApiResponse
import com.artera.movieexpertdagger.core.data.source.remote.response.MovieResponse
import com.artera.movieexpertdagger.core.data.source.remote.response.TvResponse
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.domain.model.Tv
import com.artera.movieexpertdagger.core.domain.repository.IMovieRepository
import com.artera.movieexpertdagger.core.utils.AppExecutors
import com.artera.movieexpertdagger.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(page: Int): Flow<Resource<List<Movie>>>  =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies(page)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun getAllTv(page: Int): Flow<Resource<List<Tv>>>  =
        object : NetworkBoundResource<List<Tv>, List<TvResponse>>() {
            override fun loadFromDB(): Flow<List<Tv>> {
                return localDataSource.getAllTv().map {
                    DataMapper.mapTvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> =
                remoteDataSource.getAllTvs(page)

            override suspend fun saveCallResult(data: List<TvResponse>) {
                val tvList = DataMapper.mapTvResponsesToEntities(data)
                localDataSource.insertTv(tvList)
            }
        }.asFlow()

    override fun getFavoriteTv(): Flow<List<Tv>> {
        return localDataSource.getFavoriteTv().map {
            DataMapper.mapTvEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTv(tv: Tv, state: Boolean) {
        val tvEntity = DataMapper.mapTvDomainToEntity(tv)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTv(tvEntity, state) }
    }


}

