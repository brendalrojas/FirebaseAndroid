package com.blrp.firebase.ui.home

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import com.blrp.firebase.data.firebase.FirebaseService
import com.blrp.firebase.data.model.GameData
import com.blrp.firebase.data.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    private val _gameId = MutableStateFlow("")
    val gameId: StateFlow<String> = _gameId


    fun joinGame(gameId: String, navigateToGame: (String, String, Boolean) -> Unit) {
        navigateToGame(gameId, createUserId(), false)
    }

    fun createGame(navigateToGame: (String, String, Boolean) -> Unit) {
        val newGame = createNewGame()
        val gameId = firebaseService.createGame(newGame)
        val userId = newGame.player1?.userId.orEmpty()
        val owner = true
        navigateToGame(gameId, userId, owner)
    }

    private fun createNewGame(): GameData {
        val currentPlayer = PlayerData(playerType = 1)
        return GameData(
            board = List(9) { 0 },
            player1 = currentPlayer,
            playerTurn = currentPlayer,
            player2 = null
        )

    }

    private fun createUserId(): String {
        return Calendar.getInstance().timeInMillis.hashCode().toString()
    }


}