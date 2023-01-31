package com.yakogdan.domain.entities.moviecards

import java.io.Serializable

class MovieCardDomain(
    val id: Long = 0,
    val title: String,
    val description: String,
    val voteAverage: Double,
    val adult: Boolean,
    val posterPath: String
) : Serializable