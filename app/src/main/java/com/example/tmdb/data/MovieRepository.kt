package com.example.tmdb.data

import com.example.tmdb.data.MovieDetails.*
import com.example.tmdb.database.MovieActorCrossRef
import com.example.tmdb.database.MovieCrewCrossRef
import com.example.tmdb.database.MovieDao
import com.example.tmdb.database.MovieGenreCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


interface MovieRepository {
    fun loadPopularMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadNowPlayingMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadUpcomingMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadTopRatedMovies(): SharedFlow<List<MovieItemViewState>>
    fun loadFavoriteMovies(): SharedFlow<List<MovieItemViewState>>
    fun removeFavoriteMovies(movieId: Int)
    fun getMovieDetails(movieId: Int): Flow<Details>
    fun getMovieCredits(movieId: Int): Flow<Credits>
    fun addToFavorite(movieDetails: Details, movieCredits: Credits)
    fun getMovieFormDatabase(movieId: Int): Flow<Details>
}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
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
    private val favoriteMoviesInitialFlow =
        flow {
            emit(movieDao.getFavouriteMovies().map { it.toMovieItemViewState() })
        }
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
    override fun loadFavoriteMovies(): SharedFlow<List<MovieItemViewState>> = favoriteMovies

    override fun removeFavoriteMovies(movieId: Int) {
        movieDao.removeMovie(movieId)
    }

    override fun getMovieCredits(movieId: Int): Flow<Credits> = flow {
        emit(movieApi.fetchMovieCredits(movieId).toMovieCredits())
    }

    override fun getMovieDetails(movieId: Int): Flow<Details> = flow {
        emit(movieApi.fetchMovieDetails(movieId).toMovieDetails())
    }

    override fun addToFavorite(movieDetails: Details, movieCredits: Credits) {
        val movieId = movieDetails.id
        val genre = movieDetails.genres
        val cast = movieCredits.cast
        val crew = movieCredits.crew

        movieDao.insertMovie(movieDetails.toMovieEntity())
        cast.forEach {
            movieDao.insertCastMember(it.toActorEntity())
            movieDao.insertMovieActorCrossRef(
                MovieActorCrossRef(
                    movieId,
                    it.id
                )
            )
        }
        crew.forEach {
            movieDao.insertCrewMember(it.toCrewEntity())
            movieDao.insertMovieCrewCrossRef(
                MovieCrewCrossRef(
                    movieId,
                    it.id
                )
            )
        }
        genre.forEach {
            movieDao.insertGenre(it.toGenreEntity())
            movieDao.insertGenreCrossRef(
                MovieGenreCrossRef(
                    movieId,
                    it.id
                )
            )
        }
    }

    override fun getMovieFormDatabase(movieId: Int): Flow<Details> {
        return movieDao.getMovieDetails(movieId).map { it.toDetails() }
    }


}
