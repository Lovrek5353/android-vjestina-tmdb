package com.example.tmdb.data.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class crewMemberResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("job")
    val job: String,
)
