package com.example.biblioteca

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.biblioteca.authentication.LoginActivity
import com.google.firebase.ktx.Firebase

import com.example.biblioteca.databinding.ActivityLoginBinding
import com.example.biblioteca.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser

        val textViewEmail = binding.textViewEmail
        val buttonSalir = binding.buttonSalir

        textViewEmail.text = user?.displayName

        buttonSalir.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            volverPantallaLogin()
        }
    }

    private fun obtenerUsuarioDeBase() {
        db.collection("usuarios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    private fun volverPantallaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

