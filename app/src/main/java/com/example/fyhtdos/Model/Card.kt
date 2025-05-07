package com.example.fyhtdos.Model

import java.util.Date

data class Card(
    val id: Int,
    var title: String,
    var description: String,
    var endDate: Date = Date(),
    var checked: Boolean = false
)