package com.blrp.firebase.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blrp.firebase.FirebaseInstance
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class FirebaseViewModel: ViewModel() {

    private val database = Firebase.database
    private val _data = MutableStateFlow<String?>(null)
    val data: StateFlow<String?> = _data

    init {
        getRealTimeDatabase()
    }

    fun writeFirebase() {
        val myRef = database.getReference("message")
        val randomValue: String = (0..100).random().toString()
        myRef.setValue("Mi primera escritura: $randomValue")
        Log.d("Firebase", "Firebase instance created ${myRef.database}")
    }

    private fun getRealTimeDatabase() {
        viewModelScope.launch {
            collectDatabaseReference().collect {
                _data.value = it.value.toString()
            }
        }
    }

    private fun collectDatabaseReference(): Flow<DataSnapshot> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Firebase", "Firebase instance created ${snapshot.value}")
                trySend(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Firebase", "Firebase instance created, error: ${error.message}")
            }
        }
        val myRef = database.reference
        myRef.addValueEventListener(listener)

        awaitClose { myRef.removeEventListener(listener) }
    }
}