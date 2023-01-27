package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.MovieCardDomain
import com.yakogdan.domain.entities.MovieGenreDomain
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


//    suspend fun getMovieCards(): Flow<List<MovieCardDomainEntity>> {
//        return if (movieCardsDbIsEmpty()) {
//            getMovieCardsFromRepo().collect {
//                addMovieCards(it)
//            }
//            getMovieCardsFromRepo()
//
//        } else {
//            getMovieCardsFromDB()
//        }
//    }

//    suspend fun getPopularMovieApi(): PopularMoviesDomain =
//        movieListRepository.getPopularMovieApi()
//
//
//    private suspend fun addMovieCards(movieCards: List<MovieCardDomain>) =
//        movieListRepository.addMovieCards(movieCards)
//
//    suspend fun clearMovieCardsDB() {
//        movieListRepository.clearMovieCardsDB()
//    }
//
//    private suspend fun movieCardsDbIsEmpty(): Boolean {
//        return movieListRepository.movieCardsDbIsEmpty()
//    }

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

//    suspend fun clearMovieGenresDB() {
//        movieListRepository.clearMovieGenresDB()
//    }

    private suspend fun movieGenresDbIsEmpty(): Boolean {
        return movieRepository.movieGenresDbIsEmpty()
    }
}