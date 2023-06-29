package com.example.biblioteca.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.biblioteca.MainActivity
import com.example.biblioteca.R
import com.example.biblioteca.databinding.ActivityRegistroBinding
import com.example.biblioteca.recyclerview.RecyclerViewEjemplo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    //Declara una instancia de FirebaseAuth.
    private lateinit var auth: FirebaseAuth

    //Inicializa una instancia de Cloud Firestore
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        //En el método onCreate(), inicializa la instancia FirebaseAuth.
        auth = Firebase.auth

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextNombre = binding.editTextNombre
        val editTextEmail = binding.editTextEmailRegistro
        val editTextContrasena = binding.editTextContrasenaRegistro

        val buttonRegistrarse = binding.buttonRegistrarse2

        val nombre = editTextNombre.text
        val email = editTextEmail.text
        val contrasena = editTextContrasena.text

        buttonRegistrarse.setOnClickListener {
            if (nombre.isNullOrBlank() || email.isNullOrBlank() || contrasena.isNullOrBlank()) {
                alertaCampoVacio()
            } else {
                registrarUsuario(email.toString(), contrasena.toString())
                agregarUsuarioDataBase(nombre.toString(), email.toString(), contrasena.toString())
            }
        }
    }

    private fun agregarUsuarioDataBase(nombre: String, email: String, contrasena: String) {
        val usuario = hashMapOf(
            "nombre" to nombre,
            "email" to email,
            "contrasena" to contrasena,
        )

        db.collection("usuarios")
            .add(usuario)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    private fun registrarUsuario(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        val i = Intent(this, RecyclerViewEjemplo::class.java)
        startActivity(i)
    }

    private fun alertaCampoVacio() {
        val text = "No puede dejar campos en blanco."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, text, duration)
        toast.show()
    }
}