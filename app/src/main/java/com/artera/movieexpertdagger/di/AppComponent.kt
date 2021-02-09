package com.artera.movieexpertdagger.di

import com.artera.movieexpertdagger.core.di.CoreComponent
import com.artera.movieexpertdagger.detail.DetailActivity
import com.artera.movieexpertdagger.movie.MovieFragment
import com.artera.movieexpertdagger.tv.TvFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, AppViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: MovieFragment)
    fun inject(fragment: TvFragment)
    fun inject(activity: DetailActivity)
}