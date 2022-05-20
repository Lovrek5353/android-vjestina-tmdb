package com.example.tmdb.data.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<castMemberResponse>,
    @SerialName("crew")
    val crew: List<crewMemberResponse>
)
