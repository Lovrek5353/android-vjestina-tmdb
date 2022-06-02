package com.example.tmdb.data.MovieDetails

data class Credits(
    val id: Int = 0,
    val cast: List<CastMember> = emptyList(),
    val crew: List<CrewMember> = emptyList()
)

fun CreditsResponse.toMovieCredits() = Credits(
    id,
    cast.map { it.toCastMember() },
    crew.map { it.toCrewMember() }
)