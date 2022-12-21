package com.yakogdan.domain.entities

class MovieCardDomainEntity(
    val title: String,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String
) : java.io.Serializable