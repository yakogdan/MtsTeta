package com.yakogdan.data.remote.entities.moviegenres

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieGenreRemote(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val title: String
)