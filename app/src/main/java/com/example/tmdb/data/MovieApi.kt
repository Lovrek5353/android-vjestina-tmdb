package com.example.tmdb.data

import com.example.tmdb.data.movieDetails.CreditsResponse
import com.example.tmdb.data.movieDetails.DetailsResponse
import com.example.tmdb.network.HTTPRoutes
import io.ktor.client.*
import io.ktor.client.request.*

//komunikacijasa REST API-jem
interface MovieApi {

    suspend fun fetchPopularMovies(): MovieResponseList
    suspend fun fetchNowPlayingMovies(): MovieResponseList
    suspend fun fetchUpcomingMovies(): MovieResponseList
    suspend fun fetchTopRatedMovies(): MovieResponseList
    suspend fun fetchMovieDetails(movieId: Int): DetailsResponse
    suspend fun fetchMovieCredits(movieId: Int): CreditsResponse

    //suspend fun getSearchMovie(query: String): MovieSearchResponse
}

internal class MovieApiImpl(private val client: HttpClient) : MovieApi {

    override suspend fun fetchPopularMovies(): MovieResponseList =
/*       client.get(
            "${HTTPRoutes.popularMovies}?api_key=${HTTPRoutes.apiKey}"
       )*/
            client.get(
                "https://api.themoviedb.org/3/movie/popular?api_key=fd51271c3fb07b2a1ac3cb7833094543"
            )

    override suspend fun fetchNowPlayingMovies(): MovieResponseList =
/*        client.get(
            "${HTTPRoutes.nowPlayingMovies}?api_key=${HTTPRoutes.apiKey}"
        )*/
        client.get(
            "https://api.themoviedb.org/3/movie/now_playing?api_key=fd51271c3fb07b2a1ac3cb7833094543"
        )

    override suspend fun fetchUpcomingMovies(): MovieResponseList =
/*        client.get(
            "${HTTPRoutes.upcomingMovies}?api_key=${HTTPRoutes.apiKey}"
        )*/
        client.get(
            "https://api.themoviedb.org/3/movie/upcoming?api_key=fd51271c3fb07b2a1ac3cb7833094543"
        )

    override suspend fun fetchTopRatedMovies(): MovieResponseList =
/*        client.get(
            "${HTTPRoutes.topRatedMovies}?api_key=${HTTPRoutes.apiKey}"
        )*/
        client.get(
            "https://api.themoviedb.org/3/movie/top_rated?api_key=fd51271c3fb07b2a1ac3cb7833094543"
        )

    override suspend fun fetchMovieDetails(movieId: Int): DetailsResponse=
        client.get(
            //"${HTTPRoutes.baseURL}movie/$movieId?api_key=${HTTPRoutes.apiKey}"
                "https://api.themoviedb.org/3/movie/$movieId?api_key=${HTTPRoutes.apiKey}"
        )

    override suspend fun fetchMovieCredits(movieId: Int): CreditsResponse=
        client.get(
            "${HTTPRoutes.baseURL}movie/$movieId/credits?api_key=${HTTPRoutes.apiKey}"
        )

}



