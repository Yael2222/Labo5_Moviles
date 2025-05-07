package com.example.fyhtdos.View

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Menú Principal", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = { navController.navigate("todo") }) {
                Text("Ver Lista TODO")
            }
            Button(onClick = { navController.navigate("sensor") }) {
                Text("Ver Información de Sensores")
            }
        }
    }
}