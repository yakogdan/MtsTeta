package com.yakogdan.data.mappers

import com.yakogdan.data.remote.entities.movieactors.ActorRemote
import com.yakogdan.data.remote.entities.movieactors.MovieActorsRemote
import com.yakogdan.domain.entities.movieactors.ActorDomain
import com.yakogdan.domain.entities.movieactors.MovieActorsDomain

object MovieActorMapper {

    fun mapActorsRemoteToDomain(movieActorsRemote: MovieActorsRemote): MovieActorsDomain =
        MovieActorsDomain(
            actors = mapActorRemoteToDomainList(movieActorsRemote.actors),
            id = movieActorsRemote.id
        )

    private fun mapActorRemoteToDomain(actorRemote: ActorRemote): ActorDomain =
        ActorDomain(
            name = actorRemote.name,
            profilePath = actorRemote.profilePath
        )

    private fun mapActorRemoteToDomainList(items: List<ActorRemote>?): List<ActorDomain>? =
        items?.map(MovieActorMapper::mapActorRemoteToDomain)
}