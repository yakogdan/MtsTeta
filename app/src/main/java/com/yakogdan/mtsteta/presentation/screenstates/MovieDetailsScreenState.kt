package com.yakogdan.mtsteta.presentation.screenstates

import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain

sealed class MovieDetailsScreenState {

    data object Error : MovieDetailsScreenState()

    data object Loading : MovieDetailsScreenState()

    class Result(val movieDetails: MovieDetailsDomain) : MovieDetailsScreenState()
}