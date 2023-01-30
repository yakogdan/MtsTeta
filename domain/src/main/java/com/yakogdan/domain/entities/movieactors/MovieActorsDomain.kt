package com.yakogdan.domain.entities.movieactors

data class MovieActorsDomain(
    val id: Long? = 0,
    val actors: List<MovieActorDomain>? = emptyList()
)