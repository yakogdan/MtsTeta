package com.yakogdan.data.mappers

import com.yakogdan.data.database.room.entities.MovieCardDb
import com.yakogdan.domain.entities.MovieCardDomain

object MovieCardMapper {

    fun mapDbToDomain(movieCardDb: MovieCardDb): MovieCardDomain =
        MovieCardDomain(
            id = movieCardDb.id,
            title = movieCardDb.title,
            description = movieCardDb.description,
            rateScore = movieCardDb.rateScore,
            ageRestriction = movieCardDb.ageRestriction,
            posterUrl = movieCardDb.posterUrl
        )

    private fun mapDomainToDb(movieCardDomain: MovieCardDomain): MovieCardDb =
        MovieCardDb(
            id = movieCardDomain.id,
            title = movieCardDomain.title,
            description = movieCardDomain.description,
            rateScore = movieCardDomain.rateScore,
            ageRestriction = movieCardDomain.ageRestriction,
            posterUrl = movieCardDomain.posterUrl
        )

//    fun mapDomainToDbWithoutId(movieCardDomainEntity: MovieCardDomainEntity): MovieCardDbEntity =
//        MovieCardDbEntity(
//            title = movieCardDomainEntity.title,
//            description = movieCardDomainEntity.description,
//            rateScore = movieCardDomainEntity.rateScore,
//            ageRestriction = movieCardDomainEntity.ageRestriction,
//            posterUrl = movieCardDomainEntity.posterUrl
//        )

    fun mapDomainToDbList(items: List<MovieCardDomain>): List<MovieCardDb> =
        items.map(::mapDomainToDb)

//    fun mapDomainToDbWithoutIdList(items: List<MovieCardDomainEntity>): List<MovieCardDbEntity> =
//        items.map(::mapDomainToDbWithoutId)
}