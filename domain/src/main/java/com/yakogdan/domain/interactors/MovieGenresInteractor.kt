package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieGenresInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomain>> =
        movieRepository.getMovieGenresFromRepo()

    private suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomain>> =
        movieRepository.getMovieGenresFromDB()

    private suspend fun addMovieGenres(movieGenres: List<MovieGenreDomain>) =
        movieRepository.addMovieGenres(movieGenres)

    private suspend fun movieGenresDbIsEmpty(): Boolean {
        return movieRepository.movieGenresDbIsEmpty()
    }
}