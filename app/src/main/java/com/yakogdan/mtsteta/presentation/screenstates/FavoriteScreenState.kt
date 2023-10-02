package com.yakogdan.mtsteta.presentation.screenstates

import com.yakogdan.domain.entities.moviecards.MovieCardDomain

sealed class FavoriteScreenState {

    object Error : FavoriteScreenState()

    object Loading : FavoriteScreenState()

    object Empty : FavoriteScreenState()

    class Result(val list: List<MovieCardDomain>) : FavoriteScreenState()
}