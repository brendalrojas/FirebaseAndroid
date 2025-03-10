package com.blrp.firebase.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blrp.firebase.ui.viewmodel.FirebaseViewModel
import com.blrp.firebase.ui.views.HomeScreen
import com.blrp.firebase.ui.views.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    firebaseViewModel: FirebaseViewModel,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.route
    ) {
        composable(ScreenRoutes.Home.route) {
            HomeScreen(
                viewModel = firebaseViewModel,
                modifier = modifier
            )
        }

        composable(ScreenRoutes.Register.route) {
            RegisterScreen(
                viewModel = firebaseViewModel,
                navController = navController,
                modifier = modifier
            )
        }
    }
}