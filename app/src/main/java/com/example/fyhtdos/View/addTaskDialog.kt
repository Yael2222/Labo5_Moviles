package com.example.fyhtdos.View

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun AddTaskDialog(onDismiss: () -> Unit, onAddTask: (String, String) -> Unit) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Nueva Tarea") },
        text = {
            Column {
                TextField(value = title, onValueChange = { title = it }, label = { Text("Título de la Tarea :V") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
            }
        },
        confirmButton = {
            Button(onClick = {
                if (title.text.isNotEmpty() && description.text.isNotEmpty()) {
                    onAddTask(title.text, description.text)
                }
            }) {
                Text("Agregar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}