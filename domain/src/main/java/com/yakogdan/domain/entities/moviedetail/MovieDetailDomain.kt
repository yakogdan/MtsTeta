package com.yakogdan.domain.entities.moviedetail

import com.yakogdan.domain.entities.MovieGenreDomain


data class MovieDetailDomain(
    val adult: Boolean,
    val genres: List<MovieGenreDomain>,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
)