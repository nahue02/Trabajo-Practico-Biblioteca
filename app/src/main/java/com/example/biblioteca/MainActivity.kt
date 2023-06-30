package com.example.biblioteca

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.recyclerview.GeneroAdapter
import com.example.biblioteca.recyclerview.GenerosCallback
import com.example.biblioteca.recyclerview.Libro
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.biblioteca.authentication.LoginActivity
import com.google.firebase.ktx.Firebase



class MainActivity : AppCompatActivity(), GenerosCallback {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getGeneros()
    }



    override fun onGenerosObtenidos(generos: List<String>) {
        val generosDistinct = generos.distinct().toMutableList()
        generosDistinct.remove("General")
        generosDistinct.add(0, "General")
        
        runOnUiThread {
            recyclerView.adapter = GeneroAdapter(generosDistinct, this)
        }
    }

    private fun getGeneros() {
        val database = FirebaseDatabase.getInstance().reference.child("libros")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val generos = mutableListOf<String>()

                for (libroSnapshot in snapshot.children) {
                    val genero = libroSnapshot.child("genero").getValue(String::class.java)
                    genero?.let {
                        generos.add(it)
                    }
                }

                generos.distinct().toMutableList().apply {
                    remove("General")
                    add(0, "General")
                }

                onGenerosObtenidos(generos)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error de lectura de la base de datos si es necesario
            }
        })
    }
}

