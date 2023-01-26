package com.yakogdan.domain.interactors

import com.yakogdan.domain.entities.moviedetail.MovieDetailDomain
import com.yakogdan.domain.repositories.MovieListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsInteractor @Inject constructor(
    private val movieListRepository: MovieListRepository
) {
    suspend fun  getMovieDetailFromApi(movieId: Long): Flow<MovieDetailDomain> =
        movieListRepository.getMovieDetailsApi(movieId)
}