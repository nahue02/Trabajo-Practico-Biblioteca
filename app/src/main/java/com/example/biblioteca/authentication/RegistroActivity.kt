package com.example.biblioteca.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.biblioteca.MainActivity
import com.example.biblioteca.R
import com.example.biblioteca.authentication.data.Usuario
import com.example.biblioteca.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    //Declara una instancia de FirebaseAuth.
    private lateinit var auth: FirebaseAuth

    //Inicializa una instancia de Cloud Firestore
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        firestore = FirebaseFirestore.getInstance()

        //En el método onCreate(), inicializa la instancia FirebaseAuth.
        auth = Firebase.auth

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextNombre = binding.editTextNombre
        val editTextEmail = binding.editTextEmailRegistro
        val editTextContrasena = binding.editTextContrasenaRegistro

        val buttonRegistrarse = binding.buttonRegistrarse2


        buttonRegistrarse.setOnClickListener {
            val nombre = editTextNombre.text
            val email = editTextEmail.text
            val contrasena = editTextContrasena.text

            if (nombre.isNullOrBlank() || email.isNullOrBlank() || contrasena.isNullOrBlank()) {
                mostrarMensaje("No pueden haber espacios vacios")
            } else if (contrasena.length < 6) {
                mostrarMensaje("La contraseña no puede tener menos de 6 caracteres")
            } else {
                registrarUsuario(nombre.toString(), email.toString(), contrasena.toString())
            }
        }
    }

    private fun registrarUsuario(nombre: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    if (user != null) {
                        val usuario = Usuario(user.uid, nombre, email, password)

                        firestore.collection("usuarios")
                            .add(usuario)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                    }
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    mostrarMensaje("Error al registrar el usuario")
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun mostrarMensaje(mensaje: String) {
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, mensaje, duration)
        toast.show()
    }

}