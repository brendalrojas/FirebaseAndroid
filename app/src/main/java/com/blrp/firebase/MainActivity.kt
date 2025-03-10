package com.blrp.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.blrp.firebase.ui.navigate.NavGraph
import com.blrp.firebase.ui.navigate.ScreenRoutes
import com.blrp.firebase.ui.theme.FirebaseTheme
import com.blrp.firebase.ui.viewmodel.FirebaseViewModel

class MainActivity : ComponentActivity() {

    private val firebaseInstance = FirebaseInstance(this)
    private val viewModel: FirebaseViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            FirebaseTheme {
                Scaffold(
                    modifier = Modifier
                        .padding(16.dp),
                    topBar = {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Firebase Realtime",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Button(onClick = {
                                navController.navigate(ScreenRoutes.Register.route) {
                                    popUpTo(0) {}
                                }
                            }) {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(R.drawable.addbook),
                                    contentDescription = "AddBook"
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        firebaseViewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}


