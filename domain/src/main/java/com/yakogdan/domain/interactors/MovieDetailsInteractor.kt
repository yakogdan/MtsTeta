package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.movieactors.MovieActorDomain
import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun getMovieDetailsFromApi(movieId: Long): Flow<MovieDetailsDomain> =
        movieRepository.getMovieDetailsFromApi(movieId)

    suspend fun getMovieActorsFromApi(movieId: Long): Flow<List<MovieActorDomain>> =
        movieRepository.getMovieActorsFromApi(movieId)
}