package com.sample.feature_discovering.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val discoveringRoute = "discover_route"

fun NavGraphBuilder.DiscoveringScreen(
    onBackClick:() -> Unit,
) {
    composable(route = discoveringRoute) {

    }
}