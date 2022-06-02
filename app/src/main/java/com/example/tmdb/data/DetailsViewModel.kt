package com.example.tmdb.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.MovieDetails.Credits
import com.example.tmdb.data.MovieDetails.Details
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent

class DetailsViewModel(var movieRespository: MovieRepository, movieId: Int): ViewModel(), KoinComponent {

    private val movieDetail= movieRespository.getMovieDetails(movieId= movieId)
    private val movieCredit=movieRespository.getMovieCredits(movieId= movieId)

    val movieDetailsFlow = movieDetail
        .stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        Details()
    )
    val movieCreditsFlow= movieCredit
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            Credits()
        )
}



