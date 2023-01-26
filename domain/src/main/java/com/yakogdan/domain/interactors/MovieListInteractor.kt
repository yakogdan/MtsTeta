package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.MovieCardDomain
import com.yakogdan.domain.entities.MovieGenreDomain
import com.yakogdan.domain.repositories.MovieListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListInteractor @Inject constructor(
    private val movieListRepository: MovieListRepository
) {

    // MovieCard

    suspend fun getMovieCardsFromApi(): Flow<List<MovieCardDomain>> =
        movieListRepository.getMovieCardsFromApi()

    suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomain>> =
        movieListRepository.getMovieCardsFromDB()


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
        movieListRepository.getMovieGenresFromRepo()

    private suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomain>> =
        movieListRepository.getMovieGenresFromDB()

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
        movieListRepository.addMovieGenres(movieGenres)

//    suspend fun clearMovieGenresDB() {
//        movieListRepository.clearMovieGenresDB()
//    }

    private suspend fun movieGenresDbIsEmpty(): Boolean {
        return movieListRepository.movieGenresDbIsEmpty()
    }
}