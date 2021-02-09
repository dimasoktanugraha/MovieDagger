package com.artera.movieexpertdagger.di

import com.artera.movieexpertdagger.core.domain.usecase.MovieInteractor
import com.artera.movieexpertdagger.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

}
