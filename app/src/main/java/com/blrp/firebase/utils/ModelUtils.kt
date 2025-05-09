package com.blrp.firebase.utils

import com.blrp.firebase.data.model.GameData
import com.blrp.firebase.data.model.PlayerData
import com.blrp.firebase.ui.model.GameModel
import com.blrp.firebase.ui.model.PlayerModel
import com.blrp.firebase.ui.model.PlayerType

fun GameData.toGameModel(): GameModel {
    return GameModel(
        gameId = gameId.orEmpty(),
        board = board?.map { PlayerType.getPlayerById(it) } ?: mutableListOf(),
        player1 = player1!!.toPlayerModel(),
        player2 = player2?.toPlayerModel(),
        playerTurn = playerTurn!!.toPlayerModel()
    )
}

fun PlayerData.toPlayerModel(): PlayerModel {
    return PlayerModel(
        userId = userId ?: "",
        playerType = PlayerType.getPlayerById(playerType ?: 0)
    )
}

fun GameModel.toGameData(): GameData {
    return GameData(
        board = board.map { it.id },
        gameId = gameId,
        player1 = player1.toPlayerData(),
        player2 = player2?.toPlayerData(),
        playerTurn = playerTurn.toPlayerData()
    )
}

fun PlayerModel.toPlayerData(): PlayerData {
    return PlayerData(
        userId = userId,
        playerType = playerType.id
    )
}