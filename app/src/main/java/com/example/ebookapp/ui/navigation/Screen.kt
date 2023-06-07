package com.example.ebookapp.ui.navigation

sealed class Screen(val route: String) {

    object Home: Screen("home")
    object Cart: Screen("cart")
    object About: Screen("about")
    object Detail: Screen("home/{bookId}") {
        fun createRoute(bookId: String) = "home/$bookId"
    }
}