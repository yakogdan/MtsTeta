package com.yakogdan.data.mappers

import com.yakogdan.data.database.room.entities.MovieCardDbEntity
import com.yakogdan.domain.entities.MovieCardDomainEntity

object MovieCardMapper {

    fun mapDbToDomain(movieCardDbEntity: MovieCardDbEntity): MovieCardDomainEntity =
        MovieCardDomainEntity(
            id = movieCardDbEntity.id,
            title = movieCardDbEntity.title,
            description = movieCardDbEntity.description,
            rateScore = movieCardDbEntity.rateScore,
            ageRestriction = movieCardDbEntity.ageRestriction,
            posterUrl = movieCardDbEntity.posterUrl
        )

    fun mapDomainToDb(movieCardDomainEntity: MovieCardDomainEntity): MovieCardDbEntity =
        MovieCardDbEntity(
            id = movieCardDomainEntity.id,
            title = movieCardDomainEntity.title,
            description = movieCardDomainEntity.description,
            rateScore = movieCardDomainEntity.rateScore,
            ageRestriction = movieCardDomainEntity.ageRestriction,
            posterUrl = movieCardDomainEntity.posterUrl
        )

//    fun mapDomainToDbWithoutId(movieCardDomainEntity: MovieCardDomainEntity): MovieCardDbEntity =
//        MovieCardDbEntity(
//            title = movieCardDomainEntity.title,
//            description = movieCardDomainEntity.description,
//            rateScore = movieCardDomainEntity.rateScore,
//            ageRestriction = movieCardDomainEntity.ageRestriction,
//            posterUrl = movieCardDomainEntity.posterUrl
//        )

    fun mapDomainToDbList(items: List<MovieCardDomainEntity>): List<MovieCardDbEntity> =
        items.map(::mapDomainToDb)

//    fun mapDomainToDbWithoutIdList(items: List<MovieCardDomainEntity>): List<MovieCardDbEntity> =
//        items.map(::mapDomainToDbWithoutId)
}