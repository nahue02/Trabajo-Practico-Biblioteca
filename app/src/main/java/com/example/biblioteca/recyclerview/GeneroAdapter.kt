package com.example.biblioteca.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.MainActivity
import com.example.biblioteca.R

class GeneroAdapter(private val generos: List<String>, private val context: MainActivity) : RecyclerView.Adapter<GeneroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genero, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genero = generos[position]
        holder.generoTextView.text = genero
        holder.libroRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        if (genero == "General" && !holder.adapterSet) {
            holder.adapterSet = true
            holder.libroRecyclerView.adapter = LibroAdapter(Libro.data)
        } else if (genero != "General") {
            holder.adapterSet = true
            holder.libroRecyclerView.adapter = LibroAdapter(getLibrosPorGenero(genero))
        }
    }

    override fun getItemCount(): Int {
        return generos.size
    }

    private fun getLibrosPorGenero(genero: String): List<Libro> {
        return Libro.data.filter { it.genero == genero }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val generoTextView: TextView = itemView.findViewById(R.id.generoTextView)
        val libroRecyclerView: RecyclerView = itemView.findViewById(R.id.libroRecyclerView)
        var adapterSet: Boolean = false
    }
}
