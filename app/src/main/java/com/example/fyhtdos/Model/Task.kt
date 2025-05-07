package com.example.fyhtdos.Model

import java.util.Date

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)