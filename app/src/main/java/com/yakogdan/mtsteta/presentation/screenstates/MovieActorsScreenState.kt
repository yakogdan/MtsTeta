package com.yakogdan.mtsteta.presentation.screenstates

import com.yakogdan.domain.entities.movieactors.MovieActorDomain

sealed class MovieActorsScreenState {

    data object Error : MovieActorsScreenState()

    data object Loading : MovieActorsScreenState()

    class Result(val actors: List<MovieActorDomain>) : MovieActorsScreenState()
}