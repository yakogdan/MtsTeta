package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.movieactors.MovieActorDomain
import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset

class MovieDetailInteractorTest {

    private val movieRepository = mock<MovieRepository>()

    @AfterEach
    fun tearDown() {
        reset(movieRepository)
    }

    @Test
    fun `should return the same movie details as in repository`() = runTest {
        val scope = CoroutineScope(Dispatchers.IO)
        val movieGenre = MovieGenreDomain(id = 2, title = "test title genre")
        val movieDetails = MovieDetailsDomain(
            id = 1,
            title = "test title",
            description = "test description",
            adult = true,
            genres = listOf(movieGenre, movieGenre),
            posterPath = "test path",
            releaseDate = "23.09.2023",
            voteAverage = 3.0
        )
        val testData = flowOf(movieDetails)
        val movieId = 7L
        `when`(movieRepository.getMovieDetailsFromApi(movieId)).thenReturn(testData)

        val interactor = MovieDetailsInteractor(movieRepository = movieRepository)

        val actual = interactor.getMovieDetailsFromApi(movieId).stateIn(scope)
        val expected = flowOf(movieDetails).stateIn(scope)

        assertEquals(expected.value, actual.value)
    }

    @Test
    fun `should return the same movie actors as in repository`() = runTest {
        val scope = CoroutineScope(Dispatchers.IO)
        val movieActor = MovieActorDomain(
            name = "test name",
            profilePath = "test profilePath"
        )
        val testData = flowOf(listOf(movieActor, movieActor))
        val movieId = 22L

        `when`(movieRepository.getMovieActorsFromApi(movieId)).thenReturn(testData)

        val interactor = MovieDetailsInteractor(movieRepository = movieRepository)

        val actual = interactor.getMovieActorsFromApi(movieId).stateIn(scope)
        val expected = flowOf(listOf(movieActor, movieActor)).stateIn(scope)

        assertEquals(expected.value, actual.value)
    }
}