package com.example.tmdb.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent


class FavoritesViewModel(var movieRespository: MovieRepository) : ViewModel(), KoinComponent {
    fun getFavoriteMovies(): SharedFlow<List<MovieItemViewState>> {
        return movieRespository.loadFavoriteMovies()
    }

}