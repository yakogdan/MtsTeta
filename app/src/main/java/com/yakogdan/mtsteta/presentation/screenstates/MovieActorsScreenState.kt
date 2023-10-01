package com.yakogdan.mtsteta.presentation.screenstates

import com.yakogdan.domain.entities.movieactors.MovieActorDomain

sealed class MovieActorsScreenState {

    object Error : MovieActorsScreenState()

    object Loading : MovieActorsScreenState()

    class Result(val actors: List<MovieActorDomain>) : MovieActorsScreenState()
}