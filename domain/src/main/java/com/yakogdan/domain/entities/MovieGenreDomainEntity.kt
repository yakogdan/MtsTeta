package com.yakogdan.domain.entities

import java.io.Serializable

data class MovieGenreDomainEntity(
    val id: Long,
    val title: String
) : Serializable