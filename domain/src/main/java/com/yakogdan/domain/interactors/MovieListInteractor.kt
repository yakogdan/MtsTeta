package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) {

    // MovieCard

    suspend fun getMovieCardsFromApi(): Flow<List<MovieCardDomain>> =
        movieRepository.getMovieCardsFromApi()


    suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomain>> =
        movieRepository.getMovieCardsFromDB()

    private suspend fun addMovieCard(movieCard: MovieCardDomain) =
        movieRepository.addMovieCard(movieCard)

    suspend fun movieCardsDbIsEmpty(): Boolean {
        return movieRepository.movieCardsDbIsEmpty()
    }

    // MovieGenre

    private suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomain>> =
        movieRepository.getMovieGenresFromRepo()

    private suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomain>> =
        movieRepository.getMovieGenresFromDB()

    suspend fun getMovieGenres(): Flow<List<MovieGenreDomain>> {
        return if (movieGenresDbIsEmpty()) {
            getMovieGenresFromRepo().collect {
                addMovieGenres(it)
            }
            getMovieGenresFromRepo()
        } else {
            getMovieGenresFromDB()
        }
    }

    private suspend fun addMovieGenres(movieGenres: List<MovieGenreDomain>) =
        movieRepository.addMovieGenres(movieGenres)

    private suspend fun movieGenresDbIsEmpty(): Boolean {
        return movieRepository.movieGenresDbIsEmpty()
    }
}