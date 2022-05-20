package com.example.tmdb.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieResponseList (
    @SerialName("results")
    val movies: List<MovieResponse>
)
