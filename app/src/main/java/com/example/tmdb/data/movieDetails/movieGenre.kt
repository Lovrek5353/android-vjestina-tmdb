package com.example.tmdb.data.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class movieGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
