package com.yakogdan.data.remote.entities.movieactors


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorRemote(
    @SerialName("name")
    val name: String? = "",
    @SerialName("profile_path")
    val profilePath: String? = ""
)