package com.yakogdan.data.mappers

import com.yakogdan.data.database.room.entities.MovieGenreDbEntity
import com.yakogdan.domain.entities.MovieGenreDomainEntity

object MovieGenreMapper {
    fun mapDbToDomain(movieGenreDbEntity: MovieGenreDbEntity): MovieGenreDomainEntity =
        MovieGenreDomainEntity(
            id = movieGenreDbEntity.id,
            title = movieGenreDbEntity.title
        )

    fun mapDomainToDb(movieGenreDomainEntity: MovieGenreDomainEntity): MovieGenreDbEntity =
        MovieGenreDbEntity(
            id = movieGenreDomainEntity.id,
            title = movieGenreDomainEntity.title
        )

    fun mapDomainToDbList(items: List<MovieGenreDomainEntity>): List<MovieGenreDbEntity> =
        items.map(MovieGenreMapper::mapDomainToDb)
}