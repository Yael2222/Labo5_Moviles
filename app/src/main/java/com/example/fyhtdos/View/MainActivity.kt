package com.example.fyhtdos.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fyhtdos.ViewModel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TaskViewModel = viewModel()
            ToDoApp(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoApp(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val showDialog by viewModel.showDialog

    Scaffold(
        topBar = { TopAppBar(title = { Text("Gestión de Tareas para Carlos xd") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.setShowDialog(true) }) {
                Text("Agregar Tarea")
            }
        }
    ) { paddingValues ->
        Lista(
            tasks = tasks,
            onTaskDelete = viewModel::deleteTask,
            onTaskToggle = viewModel::toggleTaskCompletion,
            modifier = Modifier.padding(paddingValues)
        )

        if (showDialog) {
            AddTaskDialog(
                onDismiss = { viewModel.setShowDialog(false) },
                onAddTask = viewModel::addTask
            )
        }
    }
}