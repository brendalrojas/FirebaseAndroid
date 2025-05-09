package com.blrp.firebase.data.firebase

import com.blrp.firebase.data.model.GameData
import com.blrp.firebase.ui.model.GameModel
import com.blrp.firebase.utils.Constants.PATH
import com.blrp.firebase.utils.toGameModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseService @Inject constructor(private val reference: DatabaseReference) {

    fun createGame(gameData: GameData): String {
        val gameReference = reference.child(PATH).push()
        val key = gameReference.key
        val newGame = gameData.copy(gameId = key)
        gameReference.setValue(newGame)
        return newGame.gameId.orEmpty()
    }

    fun joinToGame(gameId: String): Flow<GameModel?>  {
        return reference.database.reference.child("$PATH/$gameId").snapshots.map { dataSnapshots ->
            dataSnapshots.getValue(GameData::class.java)?.toGameModel()
        }
    }

    fun updateGame(gameData: GameData){
        if (gameData.gameId != null){
            reference.child(PATH).child(gameData.gameId).setValue(gameData)
        }
    }


}