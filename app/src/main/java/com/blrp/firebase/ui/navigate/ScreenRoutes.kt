package com.blrp.firebase.ui.navigate

sealed class ScreenRoutes(val route: String) {
    data object Home : ScreenRoutes("home")
    data object Register : ScreenRoutes("register")
}