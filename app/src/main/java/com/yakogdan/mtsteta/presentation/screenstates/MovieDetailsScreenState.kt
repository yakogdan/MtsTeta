package com.yakogdan.mtsteta.presentation.screenstates

import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain

sealed class MovieDetailsScreenState {

    object Error : MovieDetailsScreenState()

    object Loading : MovieDetailsScreenState()

    class Result(val movieDetails: MovieDetailsDomain) : MovieDetailsScreenState()
}