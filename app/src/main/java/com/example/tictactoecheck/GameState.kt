package com.example.tictactoecheck

enum class BoardCellValue {
    CIRCLE,
    CROSS,
    NONE
}

data class GameState(
    var playerCircleCount:Int =0,
    var playerCrossCount: Int = 0,
    var drawCount: Int = 0,
    var hintText: String = "Player 'O' turn",
    val currentTurn: BoardCellValue = BoardCellValue.CIRCLE,
    var victoryType: VictoryType = VictoryType.NONE,
    var hasWon: Boolean = false
)

enum class VictoryType {
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    HORIZONTAL4,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    VERTICAL4,
    CROSS1,
    CROSS2,
    NONE
}