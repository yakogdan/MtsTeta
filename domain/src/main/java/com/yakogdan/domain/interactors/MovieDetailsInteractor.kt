package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.movieactors.MovieActorsDomain
import com.yakogdan.domain.entities.moviedetail.MovieDetailDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun  getMovieDetailFromApi(movieId: Long): Flow<MovieDetailDomain> =
        movieRepository.getMovieDetailsApi(movieId)

    suspend fun getMovieActorsFromApi(movieId: Long): Flow<MovieActorsDomain> =
        movieRepository.getMovieActors(movieId)
}