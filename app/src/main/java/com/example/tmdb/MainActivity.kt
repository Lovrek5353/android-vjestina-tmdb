package com.example.tmdb


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.tmdb.data.*
import com.example.tmdb.screens.DetailsScreen
import com.example.tmdb.screens.Router.currentScreen
import com.example.tmdb.screens.Screen
import com.example.tmdb.screens.StartScreen
import com.example.tmdb.screens.StartScreenTab
import com.example.tmdb.ui.theme.TmdbTheme
import org.koin.androidx.compose.get
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules( FavoritesModule, homeModule, DetailsModule, favoriteDatabaseModule, repositoryModule, apiModule, httpClientModule )
        }
        setContent {
            TmdbTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    when(currentScreen){

                        is Screen.StartScreen -> {
                            val tab: StartScreenTab = (currentScreen as Screen.StartScreen).tab
                            StartScreen(mainScreenTab= tab)
                        }
                        is Screen.Details -> {
                            val movieId: Int = (currentScreen as Screen.Details).movieId
                            DetailsScreen(movieId=movieId , viewModel = (DetailsViewModel(movieRespository = get(), movieId = movieId)))
                        }
                    }
                }
            }
        }
    }
}
