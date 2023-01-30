package com.yakogdan.data.remote.entities.moviecards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCardRemote(

    @SerialName("id")
    val id: Long,

    @SerialName("title")
    val title: String,

    @SerialName("overview")
    val description: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("poster_path")
    val posterPath: String
)