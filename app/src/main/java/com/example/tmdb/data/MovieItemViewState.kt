package com.example.tmdb.data

import com.example.tmdb.database.MovieCardEntity

data class MovieItemViewState(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    var isFavorite: Boolean
)

fun MovieResponse.toMovieItemViewState(isFavorite: Boolean) = MovieItemViewState(
    id,
    title,
    overview,
    poster_path,
    isFavorite
)

fun MovieCardEntity.toMovieItemViewState() = MovieItemViewState(
    id,
    title,
    overview,
    posterPath,
    favorite
)