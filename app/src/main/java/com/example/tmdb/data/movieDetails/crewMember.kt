package com.example.tmdb.data.movieDetails

data class crewMember(
    val id: Int,
    val name: String,
    val job: String,
)

fun crewMemberResponse.toCrewMember() = crewMember(
    id,
    name,
    job,
)