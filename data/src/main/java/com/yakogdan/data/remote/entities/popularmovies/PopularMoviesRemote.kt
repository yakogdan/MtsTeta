package com.yakogdan.data.remote.entities.popularmovies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMoviesRemote(
    @SerialName("page")
    val page: Int = 1,

    @SerialName("results")
    val results: List<PopularMovieItemRemote> = emptyList(),
)