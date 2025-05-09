package com.blrp.firebase.data.model

data class GameData(
    val board: List<Int>? = null,
    val gameId: String? = null,
    val player1: PlayerData? = null,
    val player2: PlayerData? = null,
    val playerTurn: PlayerData? = null,
)