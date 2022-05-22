package com.example.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.tmdb.data.DetailsViewModel
import com.example.tmdb.data.FavoritesViewModel
import com.example.tmdb.data.HomeViewModel
import com.example.tmdb.screens.*
import com.example.tmdb.ui.theme.TmdbTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        val HomeModel: HomeViewModel by viewModels()
        val FavoritesModel: FavoritesViewModel by viewModels()
        val DetailsViewModel: DetailsViewModel by viewModels()*/
        setContent {
            TmdbTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    when(Router.currentScreen){
                        Screen.HomeScreen -> MainScreen()
                        Screen.Favorites -> FavoritesScreen()
                        Screen.Details -> DetailsScreen()
                        Screen.StartScreen -> StartScreen()
                    }
                }
            }
        }
    }
}
