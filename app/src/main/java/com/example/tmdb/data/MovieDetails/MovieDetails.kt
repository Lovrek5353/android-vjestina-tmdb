package com.example.tmdb.data.MovieDetails


data class Details(
    val id: Int = 0,
    val title: String = "",
    val posterPath: String= "",
    val releaseDate: String = "",
    val runtime: Int? = 0,
    val originalTitle: String="",
    val genres: List<MovieGenre> = emptyList(),
    val overview: String= "",
    val score: Float=0f
)

fun DetailsResponse.toMovieDetails()= Details(
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




