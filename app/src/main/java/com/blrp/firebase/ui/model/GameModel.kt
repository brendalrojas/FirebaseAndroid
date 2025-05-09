package com.blrp.firebase.ui.model

data class GameModel (
    val gameId: String,
    val board: List<PlayerType>,
    val player1: PlayerModel,
    val player2: PlayerModel? = null,
    val playerTurn: PlayerModel,
    val isGameReady: Boolean = false,
    val isMyTurn: Boolean = false
)

