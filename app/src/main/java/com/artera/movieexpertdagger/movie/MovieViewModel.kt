package com.artera.movieexpertdagger.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.artera.movieexpertdagger.core.data.Resource
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovie(page: Int) : LiveData<Resource<List<Movie>>> = movieUseCase.getAllMovie(page).asLiveData()

}