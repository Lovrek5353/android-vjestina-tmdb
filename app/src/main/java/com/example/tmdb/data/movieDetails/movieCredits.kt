package com.example.tmdb.data.movieDetails

data class movieCredits(
    val id: Int = 0,
    val cast: List<castMember> = emptyList(),
    val crew: List<crewMember> = emptyList()
)

fun CreditsResponse.toMovieCredits() = movieCredits(
    id,
    cast.map { it.tocastMember() },
    crew.map { it.toCrewMember() }
)