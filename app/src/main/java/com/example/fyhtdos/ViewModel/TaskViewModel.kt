package com.example.fyhtdos.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fyhtdos.Model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog

    fun setShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            val newTask = Task(id = _tasks.value.size + 1, title = title, description = description)
            _tasks.update { currentTasks -> currentTasks + newTask }
            _showDialog.value = false
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            _tasks.update { currentTasks -> currentTasks.filter { it.id != task.id } }
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            _tasks.update { currentTasks ->
                currentTasks.map {
                    if (it.id == task.id) it.copy(isCompleted = !it.isCompleted) else it
                }
            }
        }
    }
}