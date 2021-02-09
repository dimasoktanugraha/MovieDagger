package com.artera.movieexpertdagger.core.utils

import com.artera.movieexpertdagger.core.data.source.local.entity.MovieEntity
import com.artera.movieexpertdagger.core.data.source.local.entity.TvEntity
import com.artera.movieexpertdagger.core.data.source.remote.response.MovieResponse
import com.artera.movieexpertdagger.core.data.source.remote.response.TvResponse
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.domain.model.Tv

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                    movieId = it.movieId,
                    title = it.title,
                    overview = it.overview,
                    vote_average = it.vote_average,
                    release_date = it.release_date,
                    poster_path = it.poster_path,
                    backdrop_path = it.backdrop_path,
                    isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                    movieId = it.movieId,
                    title = it.title,
                    overview = it.overview,
                    vote_average = it.vote_average,
                    release_date = it.release_date,
                    poster_path = it.poster_path,
                    backdrop_path = it.backdrop_path,
                    isFavorite = it.isFavorite
            )
        }

    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
            movieId = input.movieId,
            title = input.title,
            overview = input.overview,
            vote_average = input.vote_average,
            release_date = input.release_date,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            isFavorite = input.isFavorite
    )

    fun mapTvResponsesToEntities(input: List<TvResponse>): List<TvEntity> {
        val tvList = ArrayList<TvEntity>()
        input.map {
            val tv = TvEntity(
                    tvId = it.tvId,
                    name = it.name,
                    overview = it.overview,
                    vote_average = it.vote_average,
                    first_air_date = it.first_air_date,
                    poster_path = it.poster_path,
                    backdrop_path = it.backdrop_path,
                    isFavorite = false
            )
            tvList.add(tv)
        }
        return tvList
    }

    fun mapTvEntitiesToDomain(input: List<TvEntity>): List<Tv> =
        input.map {
            Tv(
                    tvId = it.tvId,
                    name = it.name,
                    overview = it.overview,
                    vote_average = it.vote_average,
                    first_air_date = it.first_air_date,
                    poster_path = it.poster_path,
                    backdrop_path = it.backdrop_path,
                    isFavorite = it.isFavorite
            )
        }

    fun mapTvDomainToEntity(input: Tv) = TvEntity(
            tvId = input.tvId,
            name = input.name,
            overview = input.overview,
            vote_average = input.vote_average,
            first_air_date = input.first_air_date,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            isFavorite = input.isFavorite
    )
}