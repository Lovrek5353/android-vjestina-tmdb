package com.example.tmdb.data.MovieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("id")
    var id: Int,
    @SerialName("cast")
    val cast: List<ResponseCastMember>,
    @SerialName("crew")
    val crew: List<ResponseCrewMember>
)


