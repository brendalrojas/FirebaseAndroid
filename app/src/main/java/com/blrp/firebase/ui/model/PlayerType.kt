package com.blrp.firebase.ui.model

sealed class PlayerType(val id: Int, val symbol: String) {
    data object FirstPlayer : PlayerType(2, "X")
    data object SecondPlayer : PlayerType(3, "O")
    data object Empty : PlayerType(0, "")

    companion object {
        fun getPlayerById(id: Int): PlayerType {
            return when (id) {
                FirstPlayer.id -> FirstPlayer
                SecondPlayer.id -> SecondPlayer
                else -> Empty
            }
        }
    }

}

