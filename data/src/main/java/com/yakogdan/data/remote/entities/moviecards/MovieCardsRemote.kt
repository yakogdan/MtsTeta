package com.yakogdan.data.remote.entities.moviecards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCardsRemote(

    @SerialName("page")
    val page: Int = 1,

    @SerialName("results")
    val results: List<MovieCardRemote> = emptyList(),
)