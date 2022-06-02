package com.example.tmdb.data

import androidx.lifecycle.ViewModel
import com.example.tmdb.data.MovieDetails.Credits
import com.example.tmdb.data.MovieDetails.Details
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    var movieRepository: MovieRepository,
) : ViewModel() {
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

    fun addToFavorite(movieId: Int) {
        val movieDetails = MutableStateFlow<Details?>(null)
        val movieCredits = MutableStateFlow<Credits?>(null)

        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.getMovieDetails(movieId).collect { movieDetails.emit(it) }
            movieRepository.getMovieCredits(movieId).collect { movieCredits.emit(it) }
            movieDetails.value?.let { movieDetails ->
                movieCredits.value?.let { movieCredits ->
                    movieRepository.addToFavorite(
                        movieDetails, movieCredits
                    )
                }

            }
        }
    }

    fun removeFromFavorite(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.removeFavoriteMovies(movieId)
        }

    }

}