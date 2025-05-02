package com.blrp.firebase.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(modifier: Modifier) {


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            GameItem()
            GameItem()
            GameItem()
        }
        Row {
            GameItem()
            GameItem()
            GameItem()
        }
        Row {
            GameItem()
            GameItem()
            GameItem()
        }
    }


}

@Composable
fun GameItem() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .border(BorderStroke(1.dp, Color(0xFFaf6c9f))),
        contentAlignment = Alignment.Center
    ) {
        Text("x")
    }
}
