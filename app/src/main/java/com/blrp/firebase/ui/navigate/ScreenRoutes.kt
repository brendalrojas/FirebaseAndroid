package com.blrp.firebase.ui.navigate

sealed class ScreenRoutes(val route: String) {
    data object Home : ScreenRoutes("home")
    data object Game : ScreenRoutes("game/{gameId}/{userId}/{owner}") {
        fun createRoute(gameId: String, userId: String, owner: Boolean): String {
            return "game/${gameId}/${userId}/${owner}"
        }
    }
}