package com.artera.movieexpertdagger.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artera.movieexpertdagger.di.ViewModelFactory
import com.artera.movieexpertdagger.core.di.ViewModelKey
import com.artera.movieexpertdagger.favorite.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class FavViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}