package com.yakogdan.domain.repositories

import com.yakogdan.domain.entities.MovieCardDomainEntity
import com.yakogdan.domain.entities.MovieGenreDomainEntity
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {

    // MovieCard

    suspend fun getMovieCardsFromRepo(): Flow<List<MovieCardDomainEntity>>

    suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomainEntity>>

    suspend fun addMovieCard(movieCard: MovieCardDomainEntity)

    suspend fun addMovieCards(movieCards: List<MovieCardDomainEntity>)

    suspend fun clearMovieCardsDB()

    suspend fun movieCardsDbIsEmpty(): Boolean

    // MovieGenre

    suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomainEntity>>

    suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomainEntity>>

    suspend fun addMovieGenre(movieGenre: MovieGenreDomainEntity)

    suspend fun addMovieGenres(movieGenres: List<MovieGenreDomainEntity>)

    suspend fun clearMovieGenresDB()

    suspend fun movieGenresDbIsEmpty(): Boolean
}