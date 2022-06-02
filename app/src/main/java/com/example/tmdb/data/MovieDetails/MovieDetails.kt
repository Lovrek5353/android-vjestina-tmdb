package com.example.tmdb.data.MovieDetails

import com.example.tmdb.database.MovieEntity
import com.example.tmdb.database.MovieEntityDetails


data class Details(
    val id: Int = 0,
    val title: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val runtime: Int? = 0,
    val originalTitle: String = "",
    val genres: List<MovieGenre> = emptyList(),
    val overview: String = "",
    val score: Float = 0f
)

fun DetailsResponse.toMovieDetails() = Details(
    id,
    title,
    posterPath,
    releaseDate,
    runtime,
    originalTitle,
    genres,
    overview,
    score
)

fun Details.toMovieEntity() = MovieEntity(
    id,
    title,
    overview,
    posterPath,
    favorite = true,
    releaseDate,
    runtime,
    originalTitle,
    score
)

fun MovieEntityDetails.toDetails() = Details(
    movie.movieId,
    movie.title,
    movie.poster_path,
    movie.releaseDate,
    movie.runtime,
    movie.originalTitle,
    genres.map { it.toMovieGenre() },
    movie.overview,
    movie.score
)




