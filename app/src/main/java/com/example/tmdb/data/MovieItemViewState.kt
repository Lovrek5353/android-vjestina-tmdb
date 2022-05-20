package com.example.tmdb.data

data class MovieItemViewState(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    var isFavorite: Boolean
)

fun MovieResponse.toMovieItemViewState(isFavorite: Boolean)=MovieItemViewState(
    id,
    title,
    overview,
    poster_path,
    //poster_path.let{"$HTTPRoutes.baseURL/$it"},
    isFavorite
)