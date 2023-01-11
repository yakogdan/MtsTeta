package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.MovieCardDomainEntity
import com.yakogdan.domain.entities.MovieGenreDomainEntity
import com.yakogdan.domain.repositories.MovieListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListInteractor @Inject constructor(
    private val movieListRepository: MovieListRepository
) {

    // MovieCard

    private suspend fun getMovieCardsFromRepo(): Flow<List<MovieCardDomainEntity>> =
        movieListRepository.getMovieCardsFromRepo()

    private suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomainEntity>> =
        movieListRepository.getMovieCardsFromDB()

    suspend fun getMovieCards(): Flow<List<MovieCardDomainEntity>> {
        return if (movieCardsDbIsEmpty()) {
            getMovieCardsFromRepo().collect {
                addMovieCards(it)
            }
            getMovieCardsFromRepo()

        } else {
            getMovieCardsFromDB()
        }
    }

//    suspend fun addMovieCard(movieCard: MovieCardDomainEntity) =
//        movieListRepository.addMovieCard(movieCard)

    private suspend fun addMovieCards(movieCards: List<MovieCardDomainEntity>) =
        movieListRepository.addMovieCards(movieCards)

//    suspend fun clearMovieCardsDB() {
//        movieListRepository.clearMovieCardsDB()
//    }

    private suspend fun movieCardsDbIsEmpty(): Boolean {
        return movieListRepository.movieCardsDbIsEmpty()
    }

    // MovieGenre

    private suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomainEntity>> =
        movieListRepository.getMovieGenresFromRepo()

    private suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomainEntity>> =
        movieListRepository.getMovieGenresFromDB()

    suspend fun getMovieGenres(): Flow<List<MovieGenreDomainEntity>> {
        return if (movieGenresDbIsEmpty()) {
            getMovieGenresFromRepo().collect {
                addMovieGenres(it)
            }
            getMovieGenresFromRepo()
        } else {
            getMovieGenresFromDB()
        }
    }

//    suspend fun addMovieGenre(movieGenre: MovieGenreDomainEntity) =
//        movieListRepository.addMovieGenre(movieGenre)

    private suspend fun addMovieGenres(movieGenres: List<MovieGenreDomainEntity>) =
        movieListRepository.addMovieGenres(movieGenres)

//    suspend fun clearMovieGenresDB() {
//        movieListRepository.clearMovieGenresDB()
//    }

    private suspend fun movieGenresDbIsEmpty(): Boolean {
        return movieListRepository.movieGenresDbIsEmpty()
    }
}