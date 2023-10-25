package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun getMovieCardsFromApi(): Flow<List<MovieCardDomain>> =
        movieRepository.getMovieCardsFromApi()

    suspend fun addMovieCard(movieCard: MovieCardDomain) =
        movieRepository.addMovieCard(movieCard)
}