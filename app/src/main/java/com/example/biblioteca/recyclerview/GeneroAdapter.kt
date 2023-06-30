package com.example.biblioteca.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.MainActivity
import com.example.biblioteca.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GeneroAdapter(private val generos: List<String>, private val context: MainActivity) :
    RecyclerView.Adapter<GeneroAdapter.ViewHolder>() {

    private val database = FirebaseDatabase.getInstance().reference.child("libros")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genero, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genero = generos[position]
        holder.generoTextView.text = genero
        holder.libroRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val query = if (genero == "General") {
            database
        } else {
            database.orderByChild("genero").equalTo(genero)
        }

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val libros = mutableListOf<Libro>()

                for (libroSnapshot in snapshot.children) {
                    val titulo = libroSnapshot.child("titulo").getValue(String::class.java)
                    val autor = libroSnapshot.child("autor").getValue(String::class.java)
                    val descripcion = libroSnapshot.child("descripcion").getValue(String::class.java)
                    val imagen = libroSnapshot.child("imagen").getValue(Int::class.java)

                    titulo?.let { titulo ->
                        autor?.let { autor ->
                            descripcion?.let { descripcion ->
                                imagen?.let { imagen ->
                                    val libro = Libro(titulo, autor, descripcion, genero, imagen)
                                    libros.add(libro)
                                }
                            }
                        }
                    }
                }

                holder.libroRecyclerView.adapter = LibroAdapter(libros)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error de lectura de la base de datos si es necesario
            }
        })
    }

    override fun getItemCount(): Int {
        return generos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val generoTextView: TextView = itemView.findViewById(R.id.generoTextView)
        val libroRecyclerView: RecyclerView = itemView.findViewById(R.id.libroRecyclerView)
        var adapterSet: Boolean = false
    }
}
