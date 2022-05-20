package com.example.tmdb.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.component.KoinComponent

class HomeViewModel(
     var movieRepository: MovieRepository,
): ViewModel(), KoinComponent {
    fun getPopularMovies(): SharedFlow<List<MovieItemViewState>> {
        return movieRepository.loadPopularMovies()
    }

    fun getNowPlayingMovies(): SharedFlow<List<MovieItemViewState>> {
        return movieRepository.loadNowPlayingMovies()
    }

    fun getUpcomingMovies(): SharedFlow<List<MovieItemViewState>> {
        return movieRepository.loadUpcomingMovies()
    }

    fun getTopRatedMovies(): SharedFlow<List<MovieItemViewState>> {
        return movieRepository.loadTopRatedMovies()
    }

    fun addToFavorite(movie: MovieItemViewState) {
        movieRepository.addFavoriteMovies(movie)
    }

    fun removeFromFavorite(movie: MovieItemViewState) {
        movieRepository.removeFavoriteMovies(movie)
    }

}