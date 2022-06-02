package com.example.tmdb.data.MovieDetails

import com.example.tmdb.database.GenreEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

fun MovieGenre.toGenreEntity() = GenreEntity(
    id,
    name
)

fun GenreEntity.toMovieGenre() = MovieGenre(
    genreId,
    genreName
)
