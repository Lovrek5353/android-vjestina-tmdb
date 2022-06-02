package com.example.tmdb.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow


class FavoritesViewModel(var movieRespository: MovieRepository) : ViewModel() {
    fun getFavoriteMovies(): SharedFlow<List<MovieItemViewState>> {
        return movieRespository.loadFavoriteMovies()
    }

}