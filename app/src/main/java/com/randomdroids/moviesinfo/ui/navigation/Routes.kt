package com.randomdroids.moviesinfo.ui.navigation

sealed class Routes(val route: String) {
    object Main : Routes("main")
    object Details : Routes("details/{movieId}")
}