package com.example.tmdb.data

import com.example.tmdb.network.HTTPRoutes

class FavoriteMoviesDatabase {
    //predstavlja bazu podataka u koju se spremaju filmovi oznaceni sa Favorite
    val FavoriteMovies= mutableListOf<MovieItemViewState>(
        MovieItemViewState(
            id = 1,
            title = "Iron Man 1",
            overview = "Overview",
            poster_path = HTTPRoutes.baseImageUrl,
            isFavorite= true
        ),
        MovieItemViewState(
            id = 2,
            title = "The lion King",
            overview = "Overview",
            poster_path = HTTPRoutes.baseImageUrl,
            isFavorite= true
        ),
        MovieItemViewState(
            id = 3,
            title = "Godzilla",
            overview = "Overview",
            poster_path = HTTPRoutes.baseImageUrl,
            isFavorite= true
        )
    )

    fun fetchFavoriteMovies(): MutableList<MovieItemViewState>{
        return FavoriteMovies;
    }

    fun addFavoriteMovie(movie: MovieItemViewState){
        FavoriteMovies.add(movie)
    }
    fun deleteFavoriteMovie(movie: MovieItemViewState){
        FavoriteMovies.remove(movie)
    }
}