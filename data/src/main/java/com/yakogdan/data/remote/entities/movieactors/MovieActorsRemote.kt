package com.yakogdan.data.remote.entities.movieactors

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieActorsRemote(
    @SerialName("cast")
    val actors: List<ActorRemote>? = emptyList(),
    @SerialName("id")
    val id: Int? = 0
)