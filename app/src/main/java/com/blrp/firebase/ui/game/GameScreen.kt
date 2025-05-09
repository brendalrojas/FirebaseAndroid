package com.blrp.firebase.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blrp.firebase.ui.model.PlayerType

@Composable
fun GameScreen(
    modifier: Modifier,
    gameViewModel: GameViewModel = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean,
) {

    val game = gameViewModel.game.collectAsState()
    val status = if (game.value?.isGameReady == true) {
        if (game.value?.isMyTurn == true) {
            "Tu turno"
        } else {
            "Turno Rival"
        }
    } else {
        "Esperando por el 2do jugador"
    }

    LaunchedEffect(true) {
        gameViewModel.joinToGame(gameId, userId, owner)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text("Partida: ${game.value?.gameId}")
            Text(status)
        }

        game.value?.board?.let {
            Row {
                GameItem(it[0]) { gameViewModel.onItemSelected(0) }
                GameItem(it[1]) { gameViewModel.onItemSelected(1) }
                GameItem(it[2]) { gameViewModel.onItemSelected(2) }
            }
            Row {
                GameItem(it[3]) { gameViewModel.onItemSelected(3) }
                GameItem(it[4]) { gameViewModel.onItemSelected(4) }
                GameItem(it[5]) { gameViewModel.onItemSelected(5) }
            }
            Row {
                GameItem(it[6]) { gameViewModel.onItemSelected(6) }
                GameItem(it[7]) { gameViewModel.onItemSelected(7) }
                GameItem(it[8]) { gameViewModel.onItemSelected(8) }
            }
        }

    }


}

@Composable
fun GameItem(playerType: PlayerType, onclickItem: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .border(BorderStroke(1.dp, Color(0xFFaf6c9f)))
            .clickable { onclickItem.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Text(playerType.symbol)
    }
}
