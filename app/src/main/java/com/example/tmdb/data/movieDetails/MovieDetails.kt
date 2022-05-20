package com.example.tmdb.data.movieDetails


data class MovieDetails(
    val id: Int = 0,
    val title: String = "",
    val posterPath: String= "",
    val releaseDate: String = "",
    val runtime: Int? = 0,
    val originalTitle: String="",
    val genres: List<movieGenre> = emptyList(),
    val overview: String= "",
    val score: Float=0f
)

fun DetailsResponse.toMovieDetails()= MovieDetails(
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




