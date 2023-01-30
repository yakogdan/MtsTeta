package com.yakogdan.data.remote.entities.moviedetails

import com.yakogdan.data.remote.entities.moviegenres.MovieGenreRemote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsRemote(

    @SerialName("id")
    val id: Long = 0,

    @SerialName("title")
    val title: String = "",

    @SerialName("overview")
    val description: String = "",

    @SerialName("adult")
    val adult: Boolean = false,

    @SerialName("genres")
    val genres: List<MovieGenreRemote> = emptyList(),

    @SerialName("poster_path")
    val posterPath: String = "",

    @SerialName("release_date")
    val releaseDate: String = "",

    @SerialName("vote_average")
    val voteAverage: Double = 0.0
)