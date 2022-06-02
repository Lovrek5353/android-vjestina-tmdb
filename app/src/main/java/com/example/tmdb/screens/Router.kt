package com.example.tmdb.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Screen() {
    data class StartScreen(val tab: StartScreenTab) : Screen()
    data class Details(val movieId: Int): Screen()
    object Favorites : Screen()
    object HomeScreen : Screen()
}

open class StartScreenTab() {
    object HomeTab : StartScreenTab()
    object FavoriteTab : StartScreenTab()
}

object Router {
    var currentScreen: Screen by mutableStateOf(Screen.StartScreen(StartScreenTab.HomeTab))
    var lastHomeTab: StartScreenTab = (currentScreen as Screen.StartScreen).tab
    fun navigateTo(destination: Screen) {
        when (destination) {

            is Screen.StartScreen -> {
                currentScreen = destination
            }
            is Screen.Details -> {
                lastHomeTab = (currentScreen as Screen.StartScreen).tab
                currentScreen = destination
            }
        }
    }

}
