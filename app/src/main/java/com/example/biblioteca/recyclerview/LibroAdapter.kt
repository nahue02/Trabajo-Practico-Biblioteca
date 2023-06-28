package com.example.biblioteca.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.R


class LibroAdapter : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    var libros = listOf<Libro>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item, parent, false)
        return LibroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = libros[position]
        holder.bind(libro)
    }

    override fun getItemCount() = libros.size

    class LibroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.libro_title)
        val description: TextView = view.findViewById(R.id.libro_description)
        val genero: TextView = view.findViewById(R.id.libro_genero)
        val autor: TextView = view.findViewById(R.id.libro_autor)

        fun bind(libro: Libro) {
            title.text = libro.titulo
            description.text = libro.descripcion
            genero.text = libro.genero
            autor.text = libro.autor

        }
    }
}