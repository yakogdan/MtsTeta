package com.yakogdan.data.remote.entities.moviedetail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailRemote(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("genres")
    val genres: List<GenreRemote> = emptyList(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("overview")
    val overview: String = "",
    @SerialName("poster_path")
    val poster_path: String = "",
    @SerialName("release_date")
    val release_date: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("vote_average")
    val vote_average: Double = 0.0
)