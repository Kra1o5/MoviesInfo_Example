package com.randomdroids.moviesinfo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.randomdroids.moviesinfo.ui.details.DetailsViewModel
import com.randomdroids.moviesinfo.ui.details.MovieScreen
import com.randomdroids.moviesinfo.ui.main.MainViewModel
import com.randomdroids.moviesinfo.ui.main.MovieList

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Main.route) {
        composable(Routes.Main.route) {
            val model = hiltViewModel<MainViewModel>()
            model.requestMoviesList()
            MovieList(viewModel = model, navController = navController)
        }
        composable(
            route = Routes.Details.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val movieId = navBackStackEntry.arguments?.getString("movieId")
            val model = hiltViewModel<DetailsViewModel>()
            movieId?.let(model::requestMovieDetails)
            MovieScreen(model)
        }
    }
}