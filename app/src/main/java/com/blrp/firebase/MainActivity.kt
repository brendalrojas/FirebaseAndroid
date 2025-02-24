package com.blrp.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blrp.firebase.ui.theme.FirebaseTheme
import com.blrp.firebase.ui.viewmodel.FirebaseViewModel

class MainActivity : ComponentActivity() {

    private val firebaseInstance = FirebaseInstance(this)
    private val viewModel: FirebaseViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FirebaseTheme {
                Scaffold { innerPadding ->
                    Screen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


}

@Composable
fun Screen(viewModel: FirebaseViewModel, modifier: Modifier) {
    val data = viewModel.data.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            data.value?.forEach {
                Card(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = it.second.title ?: "",
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(
                            onClick = {
                                viewModel.removeFromDatabase(it.first)
                            }) {
                            Text(
                                text = "Eliminar"
                            )
                        }
                    }
                }
            }
        }
        item {
            Button(onClick = {
                viewModel.writeFirebase()
            }) {
                Text(
                    text = "Actualizar información",
                    modifier = Modifier
                )
            }
        }
    }
}
