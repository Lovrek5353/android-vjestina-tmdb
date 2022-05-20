package com.example.tmdb.data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val poster_path: String

)
