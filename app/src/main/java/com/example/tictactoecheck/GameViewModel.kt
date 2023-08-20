package com.example.tictactoecheck

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class GameViewModel: ViewModel() {

    var state by mutableStateOf(GameState())
        private set

    var boardItem: MutableMap<Int, BoardCellValue> by mutableStateOf(mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE,
//        10 to BoardCellValue.NONE,
//        11 to BoardCellValue.NONE,
//        12 to BoardCellValue.NONE,
//        13 to BoardCellValue.NONE,
//        14 to BoardCellValue.NONE,
//        15 to BoardCellValue.NONE,
//        16 to BoardCellValue.NONE
    ))

    private var k =0

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAction(action: UserAction) {
        when(action) {
            is UserAction.PlayAgainButtonClicked -> {
                boardItem = mutableMapOf(
                    1 to BoardCellValue.NONE,
                    2 to BoardCellValue.NONE,
                    3 to BoardCellValue.NONE,
                    4 to BoardCellValue.NONE,
                    5 to BoardCellValue.NONE,
                    6 to BoardCellValue.NONE,
                    7 to BoardCellValue.NONE,
                    8 to BoardCellValue.NONE,
                    9 to BoardCellValue.NONE,
//                    10 to BoardCellValue.NONE,
//                    11 to BoardCellValue.NONE,
//                    12 to BoardCellValue.NONE,
//                    13 to BoardCellValue.NONE,
//                    14 to BoardCellValue.NONE,
//                    15 to BoardCellValue.NONE,
//                    16 to BoardCellValue.NONE
                )
                var ht = ""
                if (state.currentTurn == BoardCellValue.CIRCLE) {
                    ht = "Player 'O' turn"
                }
                else {
                    ht = "Player 'X' turn"
                }

                state = state.copy(victoryType = VictoryType.NONE,
                    hasWon = false,

                    hintText = ht
                )
                k=0
            }
            is UserAction.BoardTapped ->
            {
                if(!state.hasWon) {
                    k++;
                    addValueToBoard(action.cellNumber)
                    if(hasWon()) {

                        state.hasWon = true
                        if(boardItem[action.cellNumber] == BoardCellValue.CROSS) {
                            state.playerCrossCount++
                            state.hintText = "Player 'X' won"
                        }
                        else if(boardItem[action.cellNumber] == BoardCellValue.CIRCLE) {
                            state.playerCircleCount++
                            state.hintText = "Player 'O' won"
                        }
                    }
                    else if(k==9)  {
                        state.drawCount++
                        state.hintText = "Draw"
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun hasWon(): Boolean {
        if(boardItem[1]==boardItem[2] && boardItem[3]==boardItem[1] && boardItem[1] != BoardCellValue.NONE) {
            state.victoryType = VictoryType.HORIZONTAL1
            return true
        }
        else if(boardItem[4]==boardItem[5] && boardItem[5]==boardItem[6] && boardItem[4] != BoardCellValue.NONE) {
            state.victoryType = VictoryType.HORIZONTAL2
            return true
        }
        else if(boardItem[7]==boardItem[8] && boardItem[8]==boardItem[9] && boardItem[9] != BoardCellValue.NONE) {
            state.victoryType = VictoryType.HORIZONTAL3
            return true
        }
        else if(boardItem[1]==boardItem[4] && boardItem[4]==boardItem[7] && boardItem[1] != BoardCellValue.NONE) {
            state.victoryType = VictoryType.VERTICAL1
            return true
        }
        else if(boardItem[2]==boardItem[5] && boardItem[5]==boardItem[8] && boardItem[2] != BoardCellValue.NONE) {
            state.victoryType = VictoryType.VERTICAL2
            return true
        }
        else if(boardItem[3]==boardItem[6] && boardItem[6]==boardItem[9] && boardItem[9] != BoardCellValue.NONE) {
            state.victoryType = VictoryType.VERTICAL3
            return true
        }
        else if(boardItem[1]==boardItem[5] && boardItem[5]==boardItem[9] && boardItem[1] != BoardCellValue.NONE) {
            state.victoryType = VictoryType.CROSS1
            return true
        }
        else if(boardItem[3]==boardItem[5] && boardItem[5]==boardItem[7] && boardItem[7] != BoardCellValue.NONE) {
            state.victoryType = VictoryType.CROSS2
            return true
        }

//        // horizontal victory
//        if(boardItem[1]==boardItem[2] && boardItem[3]==boardItem[1] && boardItem[4]==boardItem[1] && boardItem[1] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.HORIZONTAL1
//            return true
//        }
//        else if(boardItem[6]==boardItem[5] && boardItem[5]==boardItem[7] && boardItem[8]==boardItem[5] && boardItem[5] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.HORIZONTAL2
//            return true
//        }
//        else if(boardItem[9]==boardItem[10] && boardItem[11]==boardItem[9] && boardItem[9]==boardItem[12] && boardItem[9] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.HORIZONTAL3
//            return true
//        }
//        else if(boardItem[13]==boardItem[14] && boardItem[13]==boardItem[15] && boardItem[13]==boardItem[16] && boardItem[13] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.HORIZONTAL4
//            return true
//        }
//
//        //vertical victory
//        else if(boardItem[1]==boardItem[5] && boardItem[1]==boardItem[9] && boardItem[1]==boardItem[13] && boardItem[1] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.VERTICAL1
//            return true
//        }
//        else if(boardItem[2]==boardItem[6] && boardItem[2]==boardItem[10] && boardItem[2]==boardItem[14] && boardItem[2] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.VERTICAL2
//            return true
//        }
//        else if(boardItem[3]==boardItem[7] && boardItem[3]==boardItem[11] && boardItem[3]==boardItem[15] && boardItem[3] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.VERTICAL3
//            return true
//        }
//        else if(boardItem[4]==boardItem[8] && boardItem[4]==boardItem[12] && boardItem[4]==boardItem[16] && boardItem[4] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.VERTICAL4
//            return true
//        }
//
//        // cross victory
//        else if(boardItem[1]==boardItem[6] && boardItem[1]==boardItem[11] && boardItem[1]==boardItem[16] && boardItem[1] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.CROSS1
//            return true
//        }
//        else if(boardItem[4]==boardItem[7] && boardItem[4]==boardItem[10] && boardItem[4]==boardItem[13] && boardItem[4] != BoardCellValue.NONE) {
//            state.victoryType = VictoryType.CROSS2
//            return true
//        }

        return false
    }

    private fun addValueToBoard(cellNumber: Int) {
        if (boardItem[cellNumber] != BoardCellValue.NONE) {
            return
        }
//        if(boardItem[cellNumber] == state.currentTurn){
//            return
//        }
        if (state.currentTurn == BoardCellValue.CIRCLE) {
            boardItem[cellNumber] = BoardCellValue.CIRCLE
            state = state.copy(
                hintText = "Player 'X' turn",
                currentTurn = BoardCellValue.CROSS
            )
        }
        else if(state.currentTurn == BoardCellValue.CROSS) {
            boardItem[cellNumber] = BoardCellValue.CROSS
            state = state.copy(
                hintText = "Player 'O' turn",
                currentTurn = BoardCellValue.CIRCLE
            )
        }
    }
}
