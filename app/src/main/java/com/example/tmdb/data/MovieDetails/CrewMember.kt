package com.example.tmdb.data.MovieDetails

import com.example.tmdb.database.CrewEntity

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

fun CrewMember.toCrewEntity() = CrewEntity(
    id,
    name,
    job
)