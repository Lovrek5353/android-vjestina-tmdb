package com.example.tmdb.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Screen() {
    object Details : Screen()
    object Favorites : Screen()
    object HomeScreen : Screen()
}

object Router {
    var currentScreen: Screen by mutableStateOf(Screen.HomeScreen)

    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }
}
