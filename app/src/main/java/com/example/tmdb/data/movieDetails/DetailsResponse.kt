package com.example.tmdb.data.movieDetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("genres")
    val genres: List<movieGenre>,
    @SerialName("overview")
    val overview: String,
    @SerialName("vote_average")
    val score: Float
)
