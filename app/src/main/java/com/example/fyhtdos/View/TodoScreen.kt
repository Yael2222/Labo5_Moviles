package com.example.fyhtdos.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fyhtdos.ViewModel.TodoViewModel
import com.example.fyhtdos.ui.theme.Cards
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(modifier: Modifier = Modifier) {
    val viewModel: TodoViewModel = viewModel()
    val cardList by remember { derivedStateOf { viewModel.cardList } }
    val openDialog by viewModel.openDialog
    val newCard by viewModel.newCard
    val openDateDialog by viewModel.openDateDialog
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Date().time)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.openDialog() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.Black)
            }
        }
    ) { padding ->
        if (openDialog) {
            Dialog(
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),
                onDismissRequest = { viewModel.closeDialog() }
            ) {
                Card {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Agregar tarea",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        TextField(
                            value = newCard.title,
                            onValueChange = { viewModel.updateNewCardTitle(it) },
                            label = { Text("Título") },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        TextField(
                            value = newCard.description,
                            onValueChange = { viewModel.updateNewCardDescription(it) },
                            label = { Text("Descripción") },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        TextField(
                            value = newCard.endDate.toString(),
                            readOnly = true,
                            onValueChange = {},
                            label = { Text("Fecha de Entrega") },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Button(onClick = { viewModel.openDateDialog() }) {
                            Text("Seleccionar Fecha")
                        }

                        if (openDateDialog) {
                            DatePickerDialog(
                                onDismissRequest = { viewModel.closeDateDialog() },
                                confirmButton = {
                                    Button(onClick = {
                                        viewModel.closeDateDialog()
                                        datePickerState.selectedDateMillis?.let {
                                            viewModel.updateNewCardEndDate(Date(it))
                                        }
                                    }) { Text("OK") }
                                }
                            ) {
                                DatePicker(state = datePickerState)
                            }
                        }

                        Button(
                            onClick = { viewModel.addCard() },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Agregar")
                        }
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = padding.calculateTopPadding() + 16.dp)
        ) {
            items(cardList.size, key = { index -> cardList[index].id }) { index ->
                val card = cardList[index]
                Cards(
                    pos = index,
                    max = cardList.size - 1,
                    title = card.title,
                    description = card.description,
                    delete = { pos -> viewModel.removeCard(pos) },
                    check = { checked, pos -> viewModel.toggleCardChecked(checked, pos) },
                    endDate = card.endDate,
                    checked = card.checked,
                    changePosition = { _, _ -> } // Logic to change position would go in ViewModel for more complex scenarios
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}