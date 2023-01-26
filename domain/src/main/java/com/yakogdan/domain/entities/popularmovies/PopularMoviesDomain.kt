package com.yakogdan.domain.entities.popularmovies


data class PopularMoviesDomain(
    val page: Int,
    val results: List<PopularMovieItemDomain>,
)