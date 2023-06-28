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


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = auth.currentUser

        val textViewEmail = binding.textViewEmail
        val buttonSalir = binding.buttonSalir

        textViewEmail.text = user?.email

        buttonSalir.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            irPantallaLogin()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
            irPantallaLogin()
        }
    }

    private fun irPantallaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

