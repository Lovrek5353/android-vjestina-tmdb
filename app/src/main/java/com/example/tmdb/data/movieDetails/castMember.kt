package com.example.tmdb.data.movieDetails

data class castMember(
    val id: Int,
    val name: String,
    val characterName: String,
    val profilePath: String?,
)

fun castMemberResponse.tocastMember() = castMember(
    id,
    name,
    characterName,
    profilePath
)