package com.artera.movieexpertdagger.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artera.movieexpertdagger.core.di.ViewModelKey
import com.artera.movieexpertdagger.detail.DetailViewModel
import com.artera.movieexpertdagger.movie.MovieViewModel
import com.artera.movieexpertdagger.tv.TvViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class AppViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindHomeViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvViewModel::class)
    abstract fun bindTvViewModel(viewModel: TvViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}