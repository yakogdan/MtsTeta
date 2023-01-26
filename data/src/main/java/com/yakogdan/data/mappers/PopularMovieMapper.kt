package com.yakogdan.data.mappers

import com.yakogdan.data.remote.entities.popularmovies.PopularMovieItemRemote
import com.yakogdan.data.remote.entities.popularmovies.PopularMoviesRemote
import com.yakogdan.domain.entities.MovieCardDomain
import com.yakogdan.domain.entities.popularmovies.PopularMovieItemDomain
import com.yakogdan.domain.entities.popularmovies.PopularMoviesDomain

object PopularMovieMapper {

    fun mapRemoteToDomain(response: PopularMoviesRemote): PopularMoviesDomain =
        PopularMoviesDomain(
            page = response.page,
            results = response.results.map { mapItemRemoteToDomain(it) },
        )

    private fun mapItemRemoteToDomain(item: PopularMovieItemRemote): PopularMovieItemDomain =
        PopularMovieItemDomain(
            adult = item.adult,
            id = item.id,
            overview = item.overview,
            poster_path = item.poster_path,
            title = item.title,
            vote_average = item.vote_average
        )

    fun mapToMovieCard(item: PopularMovieItemDomain): MovieCardDomain {
        val ageRestriction = if (item.adult) {
            18
        } else {
            12
        }
        return MovieCardDomain(
            id = item.id.toLong(),
            title = item.title,
            description = item.overview,
            rateScore = item.vote_average,
            ageRestriction = ageRestriction,
            posterUrl = item.poster_path
        )
    }
}