package com.example.tmdb.data.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class castMemberResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("character")
    val characterName: String,
    @SerialName("profile_path")
    val profilePath: String?,
)
