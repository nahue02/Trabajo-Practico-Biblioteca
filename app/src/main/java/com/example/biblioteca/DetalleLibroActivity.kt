package com.example.biblioteca

import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.biblioteca.databinding.ActivityDetalleLibroBinding
import com.example.biblioteca.databinding.ActivityPerfilBinding
import com.example.biblioteca.recyclerview.Libro


class DetalleLibroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleLibroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_libro)

        binding = ActivityDetalleLibroBinding.inflate((layoutInflater))
        setContentView(binding.root)

        val actionBar = binding.toolbarWithBackButton
        setSupportActionBar(actionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val libro = intent.getSerializableExtra("libro") as? Libro
        if (libro != null) {

            val tituloTextView = findViewById<TextView>(R.id.tituloTextView)
            val autorTextView = findViewById<TextView>(R.id.autorTextView)
            val descripcionTextView = findViewById<TextView>(R.id.descripcionTextView)

            supportActionBar?.title = libro.titulo
            tituloTextView.text = libro.titulo
            autorTextView.text = libro.autor
            descripcionTextView.text = libro.descripcion
            // Aquí puedes usar los datos del libro para mostrarlos en la pantalla de detalle
            // por ejemplo, puedes establecer el título, autor, descripción, etc. en los TextView correspondientes.
        }
    }

}

