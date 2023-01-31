package com.yakogdan.domain.entities.moviecards


data class MovieCardsDomain(
    val page: Int,
    val results: List<MovieCardDomain>,
)