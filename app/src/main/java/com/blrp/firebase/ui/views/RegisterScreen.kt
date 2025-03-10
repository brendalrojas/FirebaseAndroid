package com.blrp.firebase.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blrp.firebase.ui.navigate.ScreenRoutes
import com.blrp.firebase.ui.viewmodel.FirebaseViewModel

@Composable
fun RegisterScreen(viewModel: FirebaseViewModel, navController: NavController, modifier: Modifier) {
    var title by remember { mutableStateOf(" ") }
    var description by remember { mutableStateOf("") }
    var isAvailable by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.padding(16.dp),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        TextField(
            modifier = Modifier.padding(8.dp),
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Disponibilidad")
            Switch(
                modifier = Modifier.padding(horizontal = 8.dp),
                checked = isAvailable,
                onCheckedChange = {
                    isAvailable = it
                }
            )
        }


        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                viewModel.writeBookFirebase(
                    title = title,
                    description = description,
                    isAvailable = isAvailable
                )
                navController.navigateUp()
            }) {
            Text(
                text = "Agregar"
            )
        }

    }
}