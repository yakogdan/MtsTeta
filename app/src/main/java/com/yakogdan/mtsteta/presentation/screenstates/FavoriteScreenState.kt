package com.yakogdan.mtsteta.presentation.screenstates

import com.yakogdan.domain.entities.moviecards.MovieCardDomain

sealed class FavoriteScreenState {

    data object Error : FavoriteScreenState()

    data object Loading : FavoriteScreenState()

    data object Empty : FavoriteScreenState()

    class Result(val list: List<MovieCardDomain>) : FavoriteScreenState()
}