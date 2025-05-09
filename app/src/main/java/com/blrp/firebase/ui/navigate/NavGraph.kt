package com.blrp.firebase.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                navigateToGame = { gameId, userId, owner ->
                    navController.navigate(ScreenRoutes.Game.createRoute(gameId, userId, owner))
                }
            )
        }

        composable(ScreenRoutes.Game.route,
            arguments = listOf(
                navArgument("gameId") { type = NavType.StringType },
                navArgument("userId") { type = NavType.StringType },
                navArgument("owner") { type = NavType.BoolType }
            )
        ) {
            GameScreen(
                modifier = modifier,
                gameId = it.arguments?.getString("gameId") ?: "",
                userId = it.arguments?.getString("userId") ?: "",
                owner = it.arguments?.getBoolean("owner") ?: false
            )
        }
    }
}