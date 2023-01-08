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

    suspend fun getMovieCardsFromRepo(): Flow<List<MovieCardDomainEntity>> =
        movieListRepository.getMovieCardsFromRepo()

    suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomainEntity>> =
        movieListRepository.getMovieCardsFromDB()

    suspend fun addMovieCard(movieCard: MovieCardDomainEntity) =
        movieListRepository.addMovieCard(movieCard)

    suspend fun addMovieCards(movieCards: List<MovieCardDomainEntity>) =
        movieListRepository.addMovieCards(movieCards)

    // MovieGenre

    suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomainEntity>> =
        movieListRepository.getMovieGenresFromRepo()

    suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomainEntity>> =
        movieListRepository.getMovieGenresFromDB()

    suspend fun addMovieGenre(movieGenre: MovieGenreDomainEntity) =
        movieListRepository.addMovieGenre(movieGenre)

    suspend fun addMovieGenres(movieGenres: List<MovieGenreDomainEntity>) =
        movieListRepository.addMovieGenres(movieGenres)
}