package com.example.biblioteca.recyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.biblioteca.R


class DetalleLibroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_libro)

        val libro = intent.getSerializableExtra("libro") as? Libro
        if (libro != null) {

            val tituloTextView = findViewById<TextView>(R.id.tituloTextView)
            val autorTextView = findViewById<TextView>(R.id.autorTextView)
            val descripcionTextView = findViewById<TextView>(R.id.descripcionTextView)

            tituloTextView.text = libro.titulo
            autorTextView.text = libro.autor
            descripcionTextView.text = libro.descripcion
            // Aquí puedes usar los datos del libro para mostrarlos en la pantalla de detalle
            // por ejemplo, puedes establecer el título, autor, descripción, etc. en los TextView correspondientes.
        }
    }

}

