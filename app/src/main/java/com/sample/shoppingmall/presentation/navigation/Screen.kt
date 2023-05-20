package com.sample.shoppingmall.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen :
        Screen(route = "shopping_home")
}