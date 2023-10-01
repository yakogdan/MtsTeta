package com.yakogdan.data.remote.entities.movieactors

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieActorsRemote(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("cast")
    val actors: List<MovieActorRemote> = emptyList()
)