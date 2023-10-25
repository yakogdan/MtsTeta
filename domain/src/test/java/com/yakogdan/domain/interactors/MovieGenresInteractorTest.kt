package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MovieGenresInteractorTest {

    private val movieRepository = mock<MovieRepository>()

    @Test
    fun `should return the same movie genres as in repository`() = runTest {
        val scope = CoroutineScope(Dispatchers.IO)
        val movieGenre = MovieGenreDomain(id = 1L, title = "test title")
        val testData = flowOf(listOf(movieGenre, movieGenre))

        Mockito.`when`(movieRepository.getMovieGenresFromRepo()).thenReturn(testData)

        val interactor = MovieGenresInteractor(movieRepository = movieRepository)

        val actual = interactor.getMovieGenresFromRepo().stateIn(scope)
        val expected = flowOf(listOf(movieGenre, movieGenre)).stateIn(scope)

        assertEquals(expected.value, actual.value)
    }
}