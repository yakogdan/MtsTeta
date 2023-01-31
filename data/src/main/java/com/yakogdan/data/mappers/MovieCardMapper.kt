package com.yakogdan.data.mappers

import com.yakogdan.data.database.room.entities.MovieCardDb
import com.yakogdan.data.remote.entities.moviecards.MovieCardRemote
import com.yakogdan.domain.entities.moviecards.MovieCardDomain

object MovieCardMapper {

    fun mapDbToDomain(movieCardDb: MovieCardDb): MovieCardDomain =
        MovieCardDomain(
            id = movieCardDb.id,
            title = movieCardDb.title,
            description = movieCardDb.description,
            voteAverage = movieCardDb.voteAverage,
            adult = movieCardDb.adult,
            posterPath = movieCardDb.posterPath
        )

    fun mapDomainToDb(movieCardDomain: MovieCardDomain): MovieCardDb =
        MovieCardDb(
            id = movieCardDomain.id,
            title = movieCardDomain.title,
            description = movieCardDomain.description,
            voteAverage = movieCardDomain.voteAverage,
            adult = movieCardDomain.adult,
            posterPath = movieCardDomain.posterPath
        )

    private fun mapRemoteToDomain(movieCardRemote: MovieCardRemote): MovieCardDomain =
        MovieCardDomain(
            id = movieCardRemote.id,
            title = movieCardRemote.title,
            description = movieCardRemote.description,
            voteAverage = movieCardRemote.voteAverage,
            adult = movieCardRemote.adult,
            posterPath = movieCardRemote.posterPath
        )

    fun mapDomainToDbList(items: List<MovieCardDomain>): List<MovieCardDb> =
        items.map(::mapDomainToDb)

    fun mapRemoteToDomainList(items: List<MovieCardRemote>): List<MovieCardDomain> =
        items.map(::mapRemoteToDomain)
}