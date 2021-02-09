package com.artera.movieexpertdagger.core.domain.repository

import com.artera.movieexpertdagger.core.data.Resource
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.domain.model.Tv
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(page: Int): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)


    fun getAllTv(page: Int): Flow<Resource<List<Tv>>>

    fun getFavoriteTv(): Flow<List<Tv>>

    fun setFavoriteTv(tv: Tv, state: Boolean)

}