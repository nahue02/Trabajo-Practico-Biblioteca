package com.example.biblioteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.recyclerview.GeneroAdapter
import com.example.biblioteca.recyclerview.Libro


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GeneroAdapter(getGeneros(), this)
    }

    private fun getGeneros(): List<String> {
        val generos = Libro.data.map { it.genero }.distinct().toMutableList()
        generos.remove("General")
        generos.add(0, "General") // Agrega "General" al inicio de la lista
        return generos
    }
}
