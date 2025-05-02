package com.blrp.firebase.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blrp.firebase.ui.game.GameScreen
import com.blrp.firebase.ui.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.route
    ) {
        composable(ScreenRoutes.Home.route) {
            HomeScreen(
                modifier = modifier,
                navigateToGame = { navController.navigate("game") }
            )
        }

        composable(ScreenRoutes.Game.route) {
            GameScreen(
                modifier = modifier
            )
        }
    }
}