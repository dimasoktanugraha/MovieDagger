package com.artera.movieexpertdagger.core.domain.usecase

import com.artera.movieexpertdagger.core.data.Resource
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.domain.model.Tv
import com.artera.movieexpertdagger.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovie(page: Int): Flow<Resource<List<Movie>>> = movieRepository.getAllMovie(page)

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun getAllTv(page: Int): Flow<Resource<List<Tv>>> = movieRepository.getAllTv(page)

    override fun getFavoriteTv(): Flow<List<Tv>> = movieRepository.getFavoriteTv()

    override fun setFavoriteTv(tv: Tv, state: Boolean) = movieRepository.setFavoriteTv(tv, state)
}