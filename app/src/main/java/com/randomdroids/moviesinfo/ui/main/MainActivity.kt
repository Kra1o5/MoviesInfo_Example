package com.randomdroids.moviesinfo.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.randomdroids.moviesinfo.ui.navigation.NavigationGraph
import com.randomdroids.moviesinfo.ui.theme.MoviesInfoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesInfoTheme {
                NavigationGraph()
            }
        }
    }
}