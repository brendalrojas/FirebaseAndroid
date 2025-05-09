package com.blrp.firebase.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blrp.firebase.R

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit,
) {

    var gameId by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.tictactoe),
            contentDescription = "",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )

        // Join a game
        OutlinedTextField(
            modifier = modifier.padding(16.dp),
            value = gameId,
            onValueChange = { gameId = it },
            label = { Text("Id game") }
        )

        Button(
            modifier = modifier.padding(16.dp),
            onClick = { homeViewModel.joinGame(gameId, navigateToGame) },
            enabled = gameId.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFaf6c9f))
        ) {
            Text("Join a game")
        }

        // Go to game
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            modifier = modifier.padding(16.dp),
            onClick = {
                homeViewModel.createGame(navigateToGame)
            },
            border = BorderStroke(1.dp, Color(0xFFaf6c9f))
        ) {
            Text("Create game", color = Color(0xFFaf6c9f))
        }

    }

}