package com.yakogdan.data.mappers

import com.yakogdan.data.database.room.entities.MovieGenreDb
import com.yakogdan.data.remote.entities.moviedetail.GenreRemote
import com.yakogdan.domain.entities.MovieGenreDomain

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


    private fun mapGenreRmToMovieGenreDm(genreRemote: GenreRemote): MovieGenreDomain =
        MovieGenreDomain(
            id = genreRemote.id.toLong(),
            title = genreRemote.title
        )

    fun mapGenreRmToMovieGenreDmList(items: List<GenreRemote>): List<MovieGenreDomain> =
        items.map(MovieGenreMapper::mapGenreRmToMovieGenreDm)
}