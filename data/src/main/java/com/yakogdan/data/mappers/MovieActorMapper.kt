package com.yakogdan.data.mappers

import com.yakogdan.data.remote.entities.movieactors.MovieActorRemote
import com.yakogdan.domain.entities.movieactors.MovieActorDomain

object MovieActorMapper {

    private fun mapActorRemoteToDomain(movieActorRemote: MovieActorRemote): MovieActorDomain =
        MovieActorDomain(
            name = movieActorRemote.name,
            profilePath = movieActorRemote.profilePath
        )

    fun mapActorRemoteToDomainList(items: List<MovieActorRemote>): List<MovieActorDomain> =
        items.map(MovieActorMapper::mapActorRemoteToDomain)
}