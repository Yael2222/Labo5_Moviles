package com.example.fyhtdos.View


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fyhtdos.Model.Task

@Composable
fun Tarjeta(task: Task, onDelete: (Task) -> Unit, onToggle: (Task) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onToggle(task) },
        colors = CardDefaults.cardColors(containerColor = if (task.isCompleted) Color.Cyan else Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.titleLarge, color = if (task.isCompleted) Color.White else Color.Black)
            Text(text = task.description, Modifier.padding(8.dp), style = MaterialTheme.typography.bodyMedium, color = if (task.isCompleted) Color.White else Color.Black)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (task.isCompleted) "Completada" else "Pendiente",
                    color = if (task.isCompleted) Color.White else Color.Red
                )
                Button(onClick = { onDelete(task) }) {
                    Text("Eliminar")
                }
            }
        }
    }
}