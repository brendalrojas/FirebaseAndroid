package com.blrp.firebase.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blrp.firebase.data.firebase.FirebaseService
import com.blrp.firebase.ui.model.GameModel
import com.blrp.firebase.ui.model.PlayerModel
import com.blrp.firebase.ui.model.PlayerType
import com.blrp.firebase.utils.toGameData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId
    private val _game = MutableStateFlow<GameModel?>(null)
    val game: StateFlow<GameModel?> = _game


    fun joinToGame(gameId: String, userId: String, owner: Boolean) {
        _userId.value = userId
        if (owner) {
            join(gameId)
        } else {
            joinGuest(gameId)
        }
    }

    private fun joinGuest(gameId: String) {
        viewModelScope.launch {

            //Suscribir una primera vez
            firebaseService.joinToGame(gameId).take(1).collect {
                var result = it
                if (result != null) {
                    result = result.copy(
                        player2 = PlayerModel(
                            userId = userId.value,
                            PlayerType.SecondPlayer
                        )
                    )
                    firebaseService.updateGame(result.toGameData())
                }
            }
            join(gameId)
        }
    }

    private fun isMyTurn(): Boolean =
        game.value?.playerTurn?.userId == userId.value


    private fun join(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).take(1).collect {
                val result = it?.copy(isGameReady = it.player2 != null)
                _game.value = result
            }
        }
    }

    fun onItemSelected(position: Int) {
        val currentGame = _game.value
        if (currentGame != null) {
            if (currentGame.isGameReady && currentGame.board[position] == PlayerType.Empty && isMyTurn()) {
                viewModelScope.launch {
                    val newBoard = currentGame.board.toMutableList()
                    newBoard[position] = getPlayer()
                    firebaseService.updateGame(currentGame.copy(board = newBoard, playerTurn = getEnemyPlayer()!!).toGameData())
                }
            }
        }
    }

    private fun getPlayer(): PlayerType {
        return when {
            (game.value?.player1?.userId == userId.value) -> PlayerType.FirstPlayer
            (game.value?.player2?.userId == userId.value) -> PlayerType.SecondPlayer
            else -> PlayerType.Empty
        }
    }

    private fun getEnemyPlayer(): PlayerModel? {
        return if (game.value?.player1?.userId == userId.value) game.value?.player2 else game.value?.player1
    }

}
