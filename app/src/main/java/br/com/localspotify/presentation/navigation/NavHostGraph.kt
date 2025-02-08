package br.com.localspotify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.localspotify.presentation.screen.home.HomeScreen

@Composable
fun NavHostGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        composable<Home> {
            HomeScreen()
        }
    }
}