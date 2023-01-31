package com.yakogdan.domain.entities

import java.io.Serializable

class MovieCardDomainEntity(
    val id: Long,
    val title: String,
    val description: String,
    val rateScore: Double,
    val ageRestriction: Int,
    val posterUrl: String
) : Serializable