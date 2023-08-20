package com.example.tictactoecheck

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoecheck.ui.theme.BlueCustom
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel()
) {
    val state = viewModel.state
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 30.dp)
        .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Player ' O ': "+state.playerCircleCount, fontSize = 16.sp)
            Text(text = "Draw: "+state.drawCount, fontSize = 16.sp)
            Text(text = "Player ' X ': "+state.playerCrossCount, fontSize = 16.sp)
        }
        Text(text = "Tic Tac Toe", fontFamily = FontFamily.Cursive,
            fontSize = 50.sp, modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = BlueCustom)
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1F)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            BoardBase()
            LazyVerticalGrid(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(25.dp),
                columns = GridCells.Fixed(3)) {
//                columns = GridCells.Fixed(4)) {
                viewModel.boardItem.forEach {
                        (cellNo, boardCellValue) -> item {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            viewModel.onAction(UserAction.BoardTapped(cellNo))
                        },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AnimatedVisibility(
                            visible = viewModel.boardItem[cellNo] != BoardCellValue.NONE,
                            enter = scaleIn(tween(150))
                        ) {
                            when(boardCellValue) {
                                BoardCellValue.CIRCLE -> Zero()
                                BoardCellValue.CROSS -> Cross()
                                BoardCellValue.NONE -> {}
                            }
                        }
//                        when(boardCellValue) {
//                            BoardCellValue.CIRCLE -> Zero()
//                            BoardCellValue.CROSS -> Cross()
//                            BoardCellValue.NONE -> {}
//                        }
                    }
                }
                }
            }
            this@Column.AnimatedVisibility(visible = state.hasWon , enter = fadeIn(tween(500))) {
                when(state.victoryType) {
                    VictoryType.HORIZONTAL1 -> HorizontalWinLine1()
                    VictoryType.HORIZONTAL2 -> HorizontalWinLine2()
                    VictoryType.HORIZONTAL3 -> HorizontalWinLine3()
//                    VictoryType.HORIZONTAL4 -> HorizontalWinLine4()
                    VictoryType.VERTICAL1 -> VerticalWinLine1()
                    VictoryType.VERTICAL2 -> VerticalWinLine2()
                    VictoryType.VERTICAL3 -> VerticalWinLine3()
//                    VictoryType.VERTICAL4 -> VerticalWinLine4()
                    VictoryType.CROSS2 -> CrossWinLine2()
                    VictoryType.CROSS1 -> CrossWinLine1()
                    else -> {}
                }
            }
//            when(state.victoryType) {
//                VictoryType.HORIZONTAL1 -> HorizontalWinLine1()
//                VictoryType.HORIZONTAL2 -> HorizontalWinLine2()
//                VictoryType.HORIZONTAL3 -> HorizontalWinLine3()
//                VictoryType.VERTICAL1 -> VerticalWinLine1()
//                VictoryType.VERTICAL2 -> VerticalWinLine2()
//                VictoryType.VERTICAL3 -> VerticalWinLine3()
//                VictoryType.CROSS2 -> CrossWinLine2()
//                VictoryType.CROSS1 -> CrossWinLine1()
//                else -> {}
//            }
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = state.hintText, fontSize = 24.sp, fontFamily = FontFamily.Cursive)
            Button(onClick = {
//                AnimatedVisibility(
//                    visible = true,
//                    enter = scaleIn(tween(300))
//                ) {
                    viewModel.onAction(UserAction.PlayAgainButtonClicked)
//                }
                             }, shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Play Again", fontSize = 16.sp)
            }
        }
    }
}