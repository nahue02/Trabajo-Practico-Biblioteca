package com.example.biblioteca.recyclerview

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.Messaging.MyFirebaseMessagingService
import com.example.biblioteca.R
import com.google.firebase.messaging.FirebaseMessaging

class RecyclerViewEjemplo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_ejemplo)

        val libroList: RecyclerView = findViewById(R.id.list) // (1)

        var libroAdapter = LibroAdapter() // (2)
        libroList.adapter = libroAdapter // (3)

        libroAdapter.libros = Libro.data // (4)


    }

}