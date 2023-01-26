package com.yakogdan.domain.entities.popularmovies

data class PopularMovieItemDomain(

    val adult: Boolean,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Double
)