package com.sample.shoppingmall.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sample.feature_discovering.navigation.discoveringScreen
import com.sample.shoppingmall.presentation.shoppingHome.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = modifier
    ) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
        discoveringScreen(onBackClick = {})

    }
}