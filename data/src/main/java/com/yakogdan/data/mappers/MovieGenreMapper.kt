package com.yakogdan.data.mappers

import com.yakogdan.data.database.room.entities.MovieGenreDb
import com.yakogdan.data.remote.entities.moviegenres.MovieGenreRemote
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain

object MovieGenreMapper {
    fun mapDbToDomain(movieGenreDb: MovieGenreDb): MovieGenreDomain =
        MovieGenreDomain(
            id = movieGenreDb.id,
            title = movieGenreDb.title
        )

    fun mapDomainToDb(movieGenreDomain: MovieGenreDomain): MovieGenreDb =
        MovieGenreDb(
            id = movieGenreDomain.id,
            title = movieGenreDomain.title
        )

    fun mapDomainToDbList(items: List<MovieGenreDomain>): List<MovieGenreDb> =
        items.map(MovieGenreMapper::mapDomainToDb)


    private fun mapGenreRmToMovieGenreDm(movieGenreRemote: MovieGenreRemote): MovieGenreDomain =
        MovieGenreDomain(
            id = movieGenreRemote.id,
            title = movieGenreRemote.title
        )

    fun mapGenreRmToMovieGenreDmList(items: List<MovieGenreRemote>): List<MovieGenreDomain> =
        items.map(MovieGenreMapper::mapGenreRmToMovieGenreDm)
}