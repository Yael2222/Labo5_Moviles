package com.example.fyhtdos.View


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fyhtdos.Model.Task

@Composable
fun Lista(
    tasks: List<Task>,
    onTaskDelete: (Task) -> Unit,
    onTaskToggle: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(tasks) { task ->
            Tarjeta(task = task, onDelete = onTaskDelete, onToggle = onTaskToggle)
        }
    }
}