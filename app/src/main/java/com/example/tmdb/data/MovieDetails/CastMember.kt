package com.example.tmdb.data.MovieDetails

data class CastMember(
    val id: Int,
    val name: String,
    val characterName: String,
    val profilePath: String?,
)

fun ResponseCastMember.toCastMember()= CastMember(
    id,
    name,
    characterName,
    profilePath
)

