package com.yakogdan.domain.entities

import java.io.Serializable

class MovieCardDomain(
    val id: Long = 0,
    val title: String,
    val description: String,
    val rateScore: Double,
    val ageRestriction: Int,
    val posterUrl: String
) : Serializable