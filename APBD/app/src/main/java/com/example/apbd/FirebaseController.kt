package com.example.apbd

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseController (context: Context) {
    private var database = Firebase.database
    private val ref = database.getReference()
    private val MContext = context

    fun newUser(userData: userData) {
            var userId = ref.push().key.toString()

        ref.child(userId).setValue(userData).apply {
            addOnCompleteListener {
                Toast.makeText(MContext,"Data saved", Toast.LENGTH_SHORT).show()
            }
            addOnFailureListener {
                Toast.makeText(MContext, "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}