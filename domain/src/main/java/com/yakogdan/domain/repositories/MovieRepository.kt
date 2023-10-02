package com.yakogdan.domain.repositories

import com.yakogdan.domain.entities.movieactors.MovieActorDomain
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    // MovieCards

    suspend fun getMovieCardsFromApi(): Flow<List<MovieCardDomain>>

    suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomain>>

    suspend fun addMovieCard(movieCard: MovieCardDomain)

    suspend fun deleteMovieCard(movieCard: MovieCardDomain)

    suspend fun movieCardsDbIsEmpty(): Boolean

    // MovieGenres

    suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomain>>

    suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomain>>

    suspend fun addMovieGenres(movieGenres: List<MovieGenreDomain>)

    suspend fun movieGenresDbIsEmpty(): Boolean

    // MovieDetails

    suspend fun getMovieDetailsFromApi(movieId: Long): Flow<MovieDetailsDomain>

    // MovieActors

    suspend fun getMovieActorsFromApi(movieId: Long): Flow<List<MovieActorDomain>>
}