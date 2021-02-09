package com.artera.movieexpertdagger.detail

import androidx.lifecycle.ViewModel
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.domain.model.Tv
import com.artera.movieexpertdagger.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, status: Boolean) =
            movieUseCase.setFavoriteMovie(movie, status)

    fun setFavoriteTv(tv: Tv, status: Boolean) =
            movieUseCase.setFavoriteTv(tv, status)
}