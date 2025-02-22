package br.com.localspotify.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.localspotify.R
import br.com.localspotify.presentation.screen.player.detail.DetailScreen
import br.com.localspotify.presentation.screen.home.HomeScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavHostGraph(
    navController: NavHostController
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