package com.artera.movieexpertdagger.favorite.di

import com.artera.movieexpertdagger.core.di.CoreComponent
import com.artera.movieexpertdagger.di.AppComponent
import com.artera.movieexpertdagger.di.AppModule
import com.artera.movieexpertdagger.di.AppScope
import com.artera.movieexpertdagger.favorite.FavoriteFragment
import com.artera.movieexpertdagger.favorite.child.FavMovieFragment
import com.artera.movieexpertdagger.favorite.child.FavTvFragment
import dagger.Component

@FavScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, FavViewModelModule::class]
)
interface FavComponent {

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavMovieFragment)
    fun inject(fragment: FavTvFragment)
}