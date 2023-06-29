package com.example.biblioteca

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase

import com.example.biblioteca.databinding.ActivityLoginBinding
import com.example.biblioteca.databinding.ActivityMainBinding
import com.example.biblioteca.recyclerview.Libro
import com.example.biblioteca.recyclerview.LibroAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth


class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val libroList: RecyclerView = findViewById(R.id.list) // (1)

        var libroAdapter = LibroAdapter() // (2)
        libroList.adapter = libroAdapter // (3)

        libroAdapter.libros = Libro.data // (4)

        val user = Firebase.auth.currentUser

        val textViewEmail = binding.textViewEmail
        val buttonSalir = binding.buttonSalir

        textViewEmail.text = user?.email

        buttonSalir.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }



}
