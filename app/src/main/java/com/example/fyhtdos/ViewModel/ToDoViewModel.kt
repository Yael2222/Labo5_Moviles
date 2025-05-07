package com.example.fyhtdos.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fyhtdos.Model.Card
import java.util.Date

class TodoViewModel : ViewModel() {
    private val _cardList = mutableStateListOf<Card>()
    val cardList: List<Card> = _cardList

    private val _openDialog = mutableStateOf(false)
    val openDialog: State<Boolean> = _openDialog

    private val _newCard = mutableStateOf(Card(0, "", ""))
    val newCard: State<Card> = _newCard

    private val _openDateDialog = mutableStateOf(false)
    val openDateDialog: State<Boolean> = _openDateDialog

    fun openDialog() {
        _openDialog.value = true
        _newCard.value = Card(0, "", "")
    }

    fun closeDialog() {
        _openDialog.value = false
    }

    fun updateNewCardTitle(title: String) {
        _newCard.value = _newCard.value.copy(title = title)
    }

    fun updateNewCardDescription(description: String) {
        _newCard.value = _newCard.value.copy(description = description)
    }

    fun openDateDialog() {
        _openDateDialog.value = true
    }

    fun closeDateDialog() {
        _openDateDialog.value = false
    }

    fun updateNewCardEndDate(date: Date) {
        _newCard.value = _newCard.value.copy(endDate = date)
    }

    fun addCard() {
        _cardList.add(_newCard.value.copy(id = _cardList.size)) // Assign a simple ID
        closeDialog()
    }

    fun removeCard(position: Int) {
        _cardList.removeAt(position)
    }

    fun toggleCardChecked(checked: Boolean, position: Int) {
        if (position in _cardList.indices) {
            val updatedCard = _cardList[position].copy(checked = checked)
            _cardList[position] = updatedCard
        }
    }

}