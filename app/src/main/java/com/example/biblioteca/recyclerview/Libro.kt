package com.example.biblioteca.recyclerview

import com.example.biblioteca.R
import java.io.Serializable

data class Libro(
    val titulo: String,
    val autor: String,
    val descripcion: String,
    val genero: String,
    val imagen: Int

    ) : Serializable




