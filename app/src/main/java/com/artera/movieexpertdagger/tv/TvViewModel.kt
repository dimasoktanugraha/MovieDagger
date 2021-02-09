
package com.artera.movieexpertdagger.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.artera.movieexpertdagger.core.data.Resource
import com.artera.movieexpertdagger.core.domain.model.Tv
import com.artera.movieexpertdagger.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class TvViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getTvs(page: Int) : LiveData<Resource<List<Tv>>> =
        movieUseCase.getAllTv(page).asLiveData()

//    val movies = movieUseCase.getAllTv().asLiveData()
}