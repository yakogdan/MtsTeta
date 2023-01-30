package com.yakogdan.domain.interactors

import android.util.Log
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) {

    // MovieCard

    private suspend fun getMovieCardsFromApi(): Flow<List<MovieCardDomain>> {
        Log.d("myTAG", "getMovieCardsFromApi: interactor")
        return movieRepository.getMovieCardsFromApi()
    }


    private suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomain>> =
        movieRepository.getMovieCardsFromDB()


    suspend fun getMovieCards(): Flow<List<MovieCardDomain>> {
        return if (movieCardsDbIsEmpty()) {
            try {
                getMovieCardsFromApi().collect {
                    addMovieCards(it)
                }
                getMovieCardsFromApi()
            } catch (e: Exception) {
                Log.e("eTAG", e.message.toString())
                getMovieCardsFromDB()
            }

        } else {
            getMovieCardsFromDB()
            try {
                getMovieCardsFromApi().collect {
                    clearMovieCardsDB()
                    addMovieCards(it)
                }
                getMovieCardsFromApi()
            } catch (e: Exception) {
                Log.e("eTAG", e.message.toString())
                getMovieCardsFromDB()
            }
        }
    }

    private suspend fun addMovieCards(movieCards: List<MovieCardDomain>) =
        movieRepository.addMovieCards(movieCards)

    private suspend fun clearMovieCardsDB() {
        movieRepository.clearMovieCardsDB()
    }

    private suspend fun movieCardsDbIsEmpty(): Boolean {
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

//    suspend fun clearMovieGenresDB() {
//        movieListRepository.clearMovieGenresDB()
//    }

    private suspend fun movieGenresDbIsEmpty(): Boolean {
        return movieRepository.movieGenresDbIsEmpty()
    }
}