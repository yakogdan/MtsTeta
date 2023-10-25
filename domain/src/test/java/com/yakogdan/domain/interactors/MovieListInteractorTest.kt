package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.moviecards.MovieCardDomain
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

class MovieListInteractorTest {

    private val movieRepository = mock<MovieRepository>()

    @Test
    fun `should return the same movie cards as in repository`() = runTest {
        val scope = CoroutineScope(Dispatchers.IO)
        val movieCard = MovieCardDomain(
            id = 1,
            title = "test title",
            description = "test description",
            voteAverage = 3.0,
            adult = true,
            posterPath = "test path"
        )
        val testData = flowOf(listOf(movieCard, movieCard))

        Mockito.`when`(movieRepository.getMovieCardsFromApi()).thenReturn(testData)

        val interactor = MovieListInteractor(movieRepository = movieRepository)

        val actual = interactor.getMovieCardsFromApi().stateIn(scope)
        val expected = flowOf(listOf(movieCard, movieCard)).stateIn(scope)

        assertEquals(expected.value, actual.value)
    }
}