package com.yakogdan.domain.entities.moviegenres

import java.io.Serializable

data class MovieGenreDomain(
    val id: Long,
    val title: String
) : Serializable