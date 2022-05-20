package com.example.tmdb.data

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent


class FavoritesViewModel(var movieRespository: MovieRepository): ViewModel(), KoinComponent {
    fun getFavoriteMovies(): SharedFlow<MutableList<MovieItemViewState>>{
        Log.d("favorite", movieRespository.loadFavoriteMovies().toString())
        return movieRespository.loadFavoriteMovies()
    }

}