package com.example.biblioteca

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

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