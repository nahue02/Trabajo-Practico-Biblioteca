package com.example.biblioteca.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.biblioteca.DetalleLibroActivity
import com.example.biblioteca.R

class LibroAdapter(private val libros: List<Libro>) : RecyclerView.Adapter<LibroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val libro = libros[position]

       holder.render(libro)


        holder.imagenImageView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetalleLibroActivity::class.java)
            intent.putExtra("libro", libro) // Pasar el libro seleccionado a la actividad de detalle
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return libros.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenImageView: ImageView = itemView.findViewById(R.id.imagenImageView)

        fun render(itemModel: Libro){
            Glide.with(imagenImageView.context).load(itemModel.imagen).into(imagenImageView)

        }

    }
}

