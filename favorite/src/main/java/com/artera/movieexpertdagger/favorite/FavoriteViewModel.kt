package com.artera.movieexpertdagger.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.artera.movieexpertdagger.core.domain.usecase.MovieUseCase
import com.artera.movieexpertdagger.favorite.di.FavScope
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
    val favoriteTv = movieUseCase.getFavoriteTv().asLiveData()
}