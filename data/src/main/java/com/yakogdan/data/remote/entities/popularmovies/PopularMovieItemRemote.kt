package com.yakogdan.data.remote.entities.popularmovies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMovieItemRemote(
    @SerialName("adult")
    val adult: Boolean,

    @SerialName("id")
    val id: Int,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val poster_path: String,

    @SerialName("title")
    val title: String,

    @SerialName("vote_average")
    val vote_average: Double
)