package com.artera.movieexpertdagger.core.data.source.local

import com.artera.movieexpertdagger.core.data.source.local.entity.MovieEntity
import com.artera.movieexpertdagger.core.data.source.local.entity.TvEntity
import com.artera.movieexpertdagger.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao)  {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(tourismList: List<MovieEntity>) = movieDao.insertMovie(tourismList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }


    fun getAllTv(): Flow<List<TvEntity>> = movieDao.getAllTv()

    fun getFavoriteTv(): Flow<List<TvEntity>> = movieDao.getFavoriteTv()

    suspend fun insertTv(tourismList: List<TvEntity>) = movieDao.insertTv(tourismList)

    fun setFavoriteTv(tv: TvEntity, newState: Boolean) {
        tv.isFavorite = newState
        movieDao.updateFavoriteTv(tv)
    }
}