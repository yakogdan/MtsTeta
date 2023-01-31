package com.yakogdan.domain.entities.moviedetails

import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain


data class MovieDetailsDomain(
    val id: Long,
    val title: String,
    val description: String,
    val adult: Boolean,
    val genres: List<MovieGenreDomain>,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double
)