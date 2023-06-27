package com.example.biblioteca.authentication.data

data class Usuario(
    val id: String,
    val nombre: String,
    val email: String,
    val contrasena: String
){
    constructor(): this("","","","")
}