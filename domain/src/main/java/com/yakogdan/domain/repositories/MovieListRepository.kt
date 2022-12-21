package com.yakogdan.domain.repositories

import com.yakogdan.domain.entities.MovieCardDomainEntity
import com.yakogdan.domain.entities.MovieGenresDomainEntity
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {

    suspend fun getMovieCards(): Flow<List<MovieCardDomainEntity>>

    suspend fun getMovieGenres(): Flow<List<MovieGenresDomainEntity>>
}