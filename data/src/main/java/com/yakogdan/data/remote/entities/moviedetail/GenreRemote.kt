package com.yakogdan.data.remote.entities.moviedetail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreRemote(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val title: String
)