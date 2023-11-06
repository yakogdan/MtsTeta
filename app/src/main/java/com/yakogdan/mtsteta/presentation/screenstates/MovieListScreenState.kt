package com.yakogdan.mtsteta.presentation.screenstates

import com.yakogdan.domain.entities.moviecards.MovieCardDomain

sealed class MovieListScreenState {

    data object Error : MovieListScreenState()

    data object Loading : MovieListScreenState()

    class Result(val list: List<MovieCardDomain>) : MovieListScreenState()
}