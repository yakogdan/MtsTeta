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

class FavoriteInteractorTest {

    private val movieRepository = mock<MovieRepository>()

    @Test
    fun `should return the same movie cards as in repository`() = runTest {
        val scope = CoroutineScope(Dispatchers.IO)
        val movieCard = MovieCardDomain(
            id = 2,
            title = "test title",
            description = "test description",
            voteAverage = 5.0,
            adult = false,
            posterPath = "test path"
        )
        val testData = flowOf(listOf(movieCard, movieCard))

        Mockito.`when`(movieRepository.getMovieCardsFromDB()).thenReturn(testData)

        val interactor = FavoriteInteractor(movieRepository = movieRepository)

        val actual = interactor.getMovieCardsFromDB().stateIn(scope)
        val expected = flowOf(listOf(movieCard, movieCard)).stateIn(scope)

        assertEquals(expected.value, actual.value)
    }

    @Test
    fun `should return the same data about the emptiness of the database as in repository`() =
        runTest {
            val testData = false
            Mockito.`when`(movieRepository.movieCardsDbIsEmpty()).thenReturn(testData)

            val interactor = FavoriteInteractor(movieRepository = movieRepository)

            val actual = interactor.movieCardsDbIsEmpty()
            val expected = false

            assertEquals(expected, actual)
        }
}