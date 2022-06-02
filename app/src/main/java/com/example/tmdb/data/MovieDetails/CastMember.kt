package com.example.tmdb.data.MovieDetails

import com.example.tmdb.database.ActorEntity

data class CastMember(
    val id: Int,
    val name: String,
    val characterName: String,
    val profilePath: String?,
)

fun ResponseCastMember.toCastMember() = CastMember(
    id,
    name,
    characterName,
    profilePath
)

fun CastMember.toActorEntity() = ActorEntity(
    id,
    name,
    characterName,
    profilePath
)

