package com.example.tmdb.data

class FavoriteMoviesDatabase {
    val FavoriteMovies = mutableListOf<MovieItemViewState>()

    fun fetchFavoriteMovies(): MutableList<MovieItemViewState> {
        return FavoriteMovies;
    }

    fun addFavoriteMovie(movie: MovieItemViewState) {
        FavoriteMovies.add(movie)
    }

    fun deleteFavoriteMovie(movie: MovieItemViewState) {
        FavoriteMovies.remove(movie)
    }
}