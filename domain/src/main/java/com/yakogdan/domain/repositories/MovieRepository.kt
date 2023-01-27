package com.yakogdan.domain.repositories

import com.yakogdan.domain.entities.MovieCardDomain
import com.yakogdan.domain.entities.MovieGenreDomain
import com.yakogdan.domain.entities.movieactors.MovieActorsDomain
import com.yakogdan.domain.entities.moviedetail.MovieDetailDomain
import com.yakogdan.domain.entities.popularmovies.PopularMoviesDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    // MovieCard

    suspend fun getPopularMovieApi(): PopularMoviesDomain

    suspend fun getMovieDetailsApi(movieId: Long): Flow<MovieDetailDomain>

    suspend fun getMovieActors(movieId: Long): Flow<MovieActorsDomain>

    suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomain>>

    suspend fun getMovieCardsFromApi(): Flow<List<MovieCardDomain>>

    suspend fun addMovieCards(movieCards: List<MovieCardDomain>)

    suspend fun clearMovieCardsDB()

    suspend fun movieCardsDbIsEmpty(): Boolean

    // MovieGenre

    suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomain>>

    suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomain>>

    suspend fun addMovieGenre(movieGenre: MovieGenreDomain)

    suspend fun addMovieGenres(movieGenres: List<MovieGenreDomain>)

    suspend fun clearMovieGenresDB()

    suspend fun movieGenresDbIsEmpty(): Boolean
}