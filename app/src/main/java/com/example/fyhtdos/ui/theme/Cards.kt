package com.example.fyhtdos.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun Cards(
    pos: Int,
    max: Int,
    title: String,
    description: String,
    delete: (Int) -> Unit,
    check: (Boolean, Int) -> Unit,
    endDate: Date,
    checked: Boolean,
    changePosition: (Int, Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (checked) TextDecoration.LineThrough else null
                )
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        textDecoration = if (checked) TextDecoration.LineThrough else null
                    )
                }
                Text(
                    text = "Entrega: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(endDate)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray,
                    textDecoration = if (checked) TextDecoration.LineThrough else null
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked -> check(isChecked, pos) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { delete(pos) }) {
                    Text("Eliminar")
                }
            }
        }
    }
}