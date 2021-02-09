package com.artera.movieexpertdagger.core.di

import com.artera.movieexpertdagger.core.data.MovieRepository
import com.artera.movieexpertdagger.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module


@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

}