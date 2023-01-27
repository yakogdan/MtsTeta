package com.yakogdan.domain.entities.movieactors

data class MovieActorsDomain(
    val actors: List<ActorDomain>? = emptyList(),
    val id: Int? = 0
)