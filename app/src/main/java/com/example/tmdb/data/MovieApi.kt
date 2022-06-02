package com.example.tmdb.data


import com.example.tmdb.data.MovieDetails.CreditsResponse
import com.example.tmdb.data.MovieDetails.DetailsResponse
import com.example.tmdb.network.HTTPRoutes
import io.ktor.client.*
import io.ktor.client.request.*


interface MovieApi {

    suspend fun fetchPopularMovies(): MovieResponseList
    suspend fun fetchNowPlayingMovies(): MovieResponseList
    suspend fun fetchUpcomingMovies(): MovieResponseList
    suspend fun fetchTopRatedMovies(): MovieResponseList
    suspend fun fetchMovieDetails(movieId: Int): DetailsResponse
    suspend fun fetchMovieCredits(movieId: Int): CreditsResponse

    //suspend fun getSearchMovie(query: String): MovieSearchResponse -will be added later
}

internal class MovieApiImpl(private val client: HttpClient) : MovieApi {

    override suspend fun fetchPopularMovies(): MovieResponseList =
      client.get(
            "${HTTPRoutes.popularMovies}?api_key=${HTTPRoutes.apiKey}"
       )

    override suspend fun fetchNowPlayingMovies(): MovieResponseList =
      client.get(
            "${HTTPRoutes.nowPlayingMovies}?api_key=${HTTPRoutes.apiKey}"
        )

    override suspend fun fetchUpcomingMovies(): MovieResponseList =
      client.get(
            "${HTTPRoutes.upcomingMovies}?api_key=${HTTPRoutes.apiKey}"
        )
    override suspend fun fetchTopRatedMovies(): MovieResponseList =
        client.get(
            "${HTTPRoutes.topRatedMovies}?api_key=${HTTPRoutes.apiKey}"
        )

    override suspend fun fetchMovieDetails(movieId: Int): DetailsResponse=
        client.get(
            "${HTTPRoutes.baseURL}movie/$movieId?api_key=${HTTPRoutes.apiKey}"
        )

    override suspend fun fetchMovieCredits(movieId: Int): CreditsResponse=
        client.get(
            "${HTTPRoutes.baseURL}movie/$movieId/credits?api_key=${HTTPRoutes.apiKey}"
        )

}



