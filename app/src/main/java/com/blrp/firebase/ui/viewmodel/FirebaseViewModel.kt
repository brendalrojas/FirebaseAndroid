package com.blrp.firebase.ui.viewmodel

import android.util.Log
import androidx.compose.animation.core.snap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blrp.firebase.FirebaseInstance
import com.blrp.firebase.data.model.Book
import com.blrp.firebase.ui.views.toResponse
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

class FirebaseViewModel : ViewModel() {

    private val database = Firebase.database
    private val myReference = database.reference
    private val _data = MutableStateFlow<List<Pair<String, Book>>?>(null)
    val data: StateFlow<List<Pair<String, Book>>?> = _data

    init {
        getRealTimeDatabase()
    }

    fun writeBookFirebase(title: String, description: String, isAvailable: Boolean = false) {
        val newLevel = myReference.push()   // Create a new level in the object
        newLevel.setValue(Book(title = title, description = description, isAvailable = isAvailable))
        Log.d("FirebaseBlrp", "Book created: $title, $description, $isAvailable")
    }

    fun writeFirebase() {
        val randomValue: String = (0..100).random().toString()
        val newLevel = myReference.push()   // Create a new level in the object
        newLevel.setValue(getGenericBookModel(randomValue))
    }

    private fun getGenericBookModel(randomValue: String) =
        Book(title = "book $randomValue", description = "description")

    private fun getRealTimeDatabase() {
        viewModelScope.launch {
            collectDatabaseReference().collect {
                _data.value = getCleanSnapshot(it)
            }
        }
    }

    private fun getCleanSnapshot(snapshot: DataSnapshot): List<Pair<String, Book>> {
        val list = snapshot.children.map { item ->
            Pair(item.key ?: "", item.getValue(Book::class.java) ?: Book())
        }
        Log.d("FirebaseBlrp", "list $list")
        return list
    }

    private fun collectDatabaseReference(): Flow<DataSnapshot> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("FirebaseBlrp", "Firebase instance created ${snapshot.value}")
                trySend(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FirebaseBlrp", "Firebase instance created, error: ${error.message}")
            }
        }
        val myRef = database.reference
        myRef.addValueEventListener(listener)

        awaitClose { myRef.removeEventListener(listener) }
    }

    fun removeFromDatabase(reference: String) {
        myReference.child(reference).removeValue()
    }

}