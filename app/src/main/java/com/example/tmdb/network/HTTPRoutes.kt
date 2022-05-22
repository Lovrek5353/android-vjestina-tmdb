package com.example.tmdb.network

object HTTPRoutes {
    const val apiKey = "fd51271c3fb07b2a1ac3cb7833094543"
    const val baseImageUrl = "https://image.tmdb.org/t/p/w500//qZCc1lty5FzX30aOCVRBLzaVmcp.png" //-->file not found on the link
    const val baseURL = "https://api.themoviedb.org/3/"
    const val popularMovies = "${baseURL}movie/popular"
    const val topRatedMovies = "${baseURL}movie/top_rated"
    const val nowPlayingMovies = "${baseURL}movie/now_playing"
    const val upcomingMovies = "${baseURL}movie/upcoming"
}