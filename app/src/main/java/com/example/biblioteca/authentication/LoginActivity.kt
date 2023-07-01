package com.example.biblioteca.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.biblioteca.HomeActivity
import com.example.biblioteca.MainActivity
import com.example.biblioteca.R
import com.example.biblioteca.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    //Declara una instancia de FirebaseAuth.
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseDatabase.getInstance()
        auth = Firebase.auth

        //XML
        val buttonRegistrarse = binding.buttonRegistrarse
        val buttonLogin = binding.buttonLogin

        val editTextEmail = binding.editTextEmail
        val editTextContrasena = binding.editTextContrasena
        //

        buttonRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text
            val contrasena = editTextContrasena.text

            if (email.isNullOrBlank() || contrasena.isNullOrBlank()) {
                alertaCampoVacio()
            } else {
                iniciarSesion(email.toString(), contrasena.toString())
            }
        }
    }

    private fun iniciarSesion(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    updateUI()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Error al iniciar sesión.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

    }

    private fun updateUI() {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
    }

    private fun alertaCampoVacio() {
        val text = "No puede dejar campos en blanco."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, text, duration)
        toast.show()
    }
}

