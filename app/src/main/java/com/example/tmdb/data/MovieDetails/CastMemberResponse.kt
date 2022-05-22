package com.example.tmdb.data.MovieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCastMember(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("character")
    val characterName: String,
    @SerialName("profile_path")
    val profilePath: String?,
)
