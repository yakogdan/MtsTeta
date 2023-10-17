package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomain>> =
        movieRepository.getMovieCardsFromDB()

    suspend fun deleteMovieCard(movieCard: MovieCardDomain) =
        movieRepository.deleteMovieCard(movieCard)

    suspend fun movieCardsDbIsEmpty(): Boolean {
        return movieRepository.movieCardsDbIsEmpty()
    }
}