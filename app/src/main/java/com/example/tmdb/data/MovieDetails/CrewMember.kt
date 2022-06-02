package com.example.tmdb.data.MovieDetails

data class CrewMember(
    val id: Int,
    val name: String,
    val job: String,
)

fun ResponseCrewMember.toCrewMember() = CrewMember(
    id,
    name,
    job,
)