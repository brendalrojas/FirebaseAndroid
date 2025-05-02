package com.blrp.firebase.ui.navigate

sealed class ScreenRoutes(val route: String) {
    data object Home : ScreenRoutes("home")
    data object Game : ScreenRoutes("game")
}