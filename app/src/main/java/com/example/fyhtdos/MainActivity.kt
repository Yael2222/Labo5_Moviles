package com.example.fyhtdos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fyhtdos.View.HomeScreen
import com.example.fyhtdos.View.SensorScreen
import com.example.fyhtdos.View.TodoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("todo") { TodoScreen() }
                composable("sensor") { SensorScreen() }
            }
        }
    }
}
