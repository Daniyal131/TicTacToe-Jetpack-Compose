package com.example.tictactoecheck

sealed class UserAction {
    object PlayAgainButtonClicked : UserAction()
    data class BoardTapped(val cellNumber: Int): UserAction()
}