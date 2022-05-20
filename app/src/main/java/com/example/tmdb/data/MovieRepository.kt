package com.example.tmdb.data

import android.util.Log
import com.example.tmdb.data.movieDetails.MovieDetails
import com.example.tmdb.data.movieDetails.movieCredits
import com.example.tmdb.data.movieDetails.toMovieCredits
import com.example.tmdb.data.movieDetails.toMovieDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


//posrednik između izvora podataka

interface MovieRepository {
    fun loadPopularMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadNowPlayingMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadUpcomingMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadTopRatedMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadFavoriteMovies(): SharedFlow<MutableList<MovieItemViewState>>
    fun addFavoriteMovies(movie: MovieItemViewState)
    fun removeFavoriteMovies(movie: MovieItemViewState)
    fun getMovieDetails(movieId: Int): Flow<MovieDetails>
    fun getMovieCredits(movieId: Int): Flow<movieCredits>

}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val favoriteDatabase: FavoriteMoviesDatabase
) : MovieRepository {

    private val flowScope = CoroutineScope(Dispatchers.Default)


    private var popularMovies = flow { emit(movieApi.fetchPopularMovies().movies.map{it.toMovieItemViewState(false)}) }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 750L),
            replay = 3
        )
    private var nowPlayingMovies = flow { emit(movieApi.fetchNowPlayingMovies().movies.map{it.toMovieItemViewState(false)}) }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 750L),
            replay = 3
        )
    private var upcomingMovies = flow { emit(movieApi.fetchUpcomingMovies().movies.map{it.toMovieItemViewState(false)}) }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 750L),
            replay = 3
        )
    private var topRatedMovies = flow { emit(movieApi.fetchTopRatedMovies().movies.map{it.toMovieItemViewState(false)}) }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 750L),
            replay = 3
        )
    private var favoriteMovies = flow { emit(favoriteDatabase.fetchFavoriteMovies()) }
        .shareIn(
            flowScope,
            SharingStarted.Lazily,
            replay = 1
        )

    override  fun loadPopularMovies(): SharedFlow<List<MovieItemViewState>> = popularMovies
    override fun loadNowPlayingMovies(): SharedFlow<List<MovieItemViewState>> = nowPlayingMovies
    override  fun loadUpcomingMovies(): SharedFlow<List<MovieItemViewState>> = upcomingMovies
    override fun loadTopRatedMovies(): SharedFlow<List<MovieItemViewState>> = topRatedMovies
    override fun loadFavoriteMovies(): SharedFlow<MutableList<MovieItemViewState>> = favoriteMovies
    override fun addFavoriteMovies(movie: MovieItemViewState) {
        favoriteDatabase.addFavoriteMovie(movie)
    }

    override fun removeFavoriteMovies(movie: MovieItemViewState) {
        favoriteDatabase.deleteFavoriteMovie(movie)
    }

    override fun getMovieCredits(movieId: Int): Flow<movieCredits> = flow {
        emit(movieApi.fetchMovieCredits(movieId).toMovieCredits())
        Log.d("credits",movieApi.fetchMovieCredits(movieId).toMovieCredits().toString() )
    }

    override fun getMovieDetails(movieId: Int): Flow<MovieDetails> = flow {
        emit(movieApi.fetchMovieDetails(movieId).toMovieDetails())
    }


}
