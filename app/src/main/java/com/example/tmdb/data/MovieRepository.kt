package com.example.tmdb.data

import com.example.tmdb.data.MovieDetails.Credits
import com.example.tmdb.data.MovieDetails.Details
import com.example.tmdb.data.MovieDetails.toMovieCredits
import com.example.tmdb.data.MovieDetails.toMovieDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


interface MovieRepository {
    fun loadPopularMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadNowPlayingMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadUpcomingMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadTopRatedMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadFavoriteMovies(): SharedFlow<MutableList<MovieItemViewState>>
    fun addFavoriteMovies(movie: MovieItemViewState)
    fun removeFavoriteMovies(movie: MovieItemViewState)
    fun getMovieDetails(movieId: Int): Flow<Details>
    fun getMovieCredits(movieId: Int): Flow<Credits>

}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val favoriteDatabase: FavoriteMoviesDatabase
) : MovieRepository {

    private val flowScope = CoroutineScope(Dispatchers.Default)


    private val popularMoviesPublisher = MutableSharedFlow<List<MovieItemViewState>>()
    private val nowPlayingMoviesPublisher = MutableSharedFlow<List<MovieItemViewState>>()
    private val upcomingMoviesPublisher = MutableSharedFlow<List<MovieItemViewState>>()
    private val topRatedMoviesPublisher = MutableSharedFlow<List<MovieItemViewState>>()
    private val favoriteMoviesPublisher = MutableSharedFlow<MutableList<MovieItemViewState>>()

    private val popularMoviesInitialFlow =
        flow { emit(movieApi.fetchPopularMovies().movies.map { it.toMovieItemViewState(false) }) }
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )

    private val nowPlayingMoviesInitialFlow =
        flow { emit(movieApi.fetchNowPlayingMovies().movies.map { it.toMovieItemViewState(false) }) }
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )
    private val upcomingMoviesInitialFlow =
        flow { emit(movieApi.fetchUpcomingMovies().movies.map { it.toMovieItemViewState(false) }) }
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )
    private val topRatedMoviesInitiralFlow =
        flow { emit(movieApi.fetchTopRatedMovies().movies.map { it.toMovieItemViewState(false) }) }
            .shareIn(
                flowScope,
                SharingStarted.WhileSubscribed(),
                replay = 1
            )
    private val favoriteMoviesInitialFlow = flow { emit(favoriteDatabase.fetchFavoriteMovies()) }
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )


    private val popularMovies = merge(
        popularMoviesPublisher,
        popularMoviesInitialFlow
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val nowPlayingMovies = merge(
        nowPlayingMoviesPublisher,
        nowPlayingMoviesInitialFlow
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val upcomingMovies = merge(
        upcomingMoviesPublisher,
        upcomingMoviesInitialFlow
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )
    private val topRatedMovies = merge(
        topRatedMoviesPublisher,
        topRatedMoviesInitiralFlow
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val favoriteMovies = merge(
        favoriteMoviesPublisher,
        favoriteMoviesInitialFlow
    )
        .shareIn(
            flowScope,
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun loadPopularMovies(): SharedFlow<List<MovieItemViewState>> = popularMovies
    override fun loadNowPlayingMovies(): SharedFlow<List<MovieItemViewState>> = nowPlayingMovies
    override fun loadUpcomingMovies(): SharedFlow<List<MovieItemViewState>> = upcomingMovies
    override fun loadTopRatedMovies(): SharedFlow<List<MovieItemViewState>> = topRatedMovies
    override fun loadFavoriteMovies(): SharedFlow<MutableList<MovieItemViewState>> = favoriteMovies

    override fun addFavoriteMovies(movie: MovieItemViewState) {
        favoriteDatabase.addFavoriteMovie(movie)
    }

    override fun removeFavoriteMovies(movie: MovieItemViewState) {
        favoriteDatabase.deleteFavoriteMovie(movie)
    }

    override fun getMovieCredits(movieId: Int): Flow<Credits> = flow {
        emit(movieApi.fetchMovieCredits(movieId).toMovieCredits())
    }

    override fun getMovieDetails(movieId: Int): Flow<Details> = flow {
        emit(movieApi.fetchMovieDetails(movieId).toMovieDetails())
    }


}
