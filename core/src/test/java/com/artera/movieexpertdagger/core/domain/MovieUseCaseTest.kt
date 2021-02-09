package com.artera.movieexpertdagger.core.domain

import com.artera.movieexpertdagger.core.data.MovieRepository
import com.artera.movieexpertdagger.core.domain.model.Movie
import com.artera.movieexpertdagger.core.domain.repository.IMovieRepository
import com.artera.movieexpertdagger.core.domain.usecase.MovieInteractor
import com.artera.movieexpertdagger.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp() {
        movieUseCase = MovieInteractor(movieRepository)
        val dummyMessage = listOf(Movie(1, "Movie", "Overview", 4.4F, "Date", "Poster", "Backdrop", false))
        Mockito.`when`(movieRepository.getAllMovie(1)).thenReturn(flow { dummyMessage })
    }

    @Test
    fun `should get data from repository`() {
        movieUseCase.getAllMovie(1)

        Mockito.verify(movieRepository).getAllMovie(1)
        Mockito.verifyNoMoreInteractions(movieRepository)
    }
}