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
import com.example.biblioteca.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    //Declara una instancia de FirebaseAuth.
    private lateinit var auth: FirebaseAuth

    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firestore = FirebaseFirestore.getInstance()

        //En el método onCreate(), inicializa la instancia FirebaseAuth.
        auth = Firebase.auth

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //XML
        val buttonRegistrarse = binding.buttonRegistrarse
        val buttonLogin = binding.buttonLogin

        val editTextEmail = binding.editTextEmail
        val editTextContrasena = binding.editTextContrasena



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

    //Cuando inicialices la actividad, verifica que el usuario haya accedido.
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun iniciarSesion(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    verificarCredencialesEnFirestore(user?.uid, email, password)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Email o Contraseña Incorrectos",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    private fun verificarCredencialesEnFirestore(userId: String?, email: String, password: String) {
        val userRef = firestore.collection("usuarios").document(userId!!)

        userRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    // El usuario existe en Firestore, verifica las credenciales
                    val storedEmail = document.getString("email")
                    val storedPassword = document.getString("password")

                    if (storedEmail == email && storedPassword == password) {
                        // Las credenciales coinciden, lleva al usuario a la nueva actividad
                        updateUI()
                        finish()
                    } else {
                        // Las credenciales no coinciden
                        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // El usuario no existe en Firestore
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Error al obtener los datos del usuario en Firestore
                Toast.makeText(this, "Error al obtener los datos del usuario", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    private fun updateUI() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun alertaCampoVacio() {
        val text = "No puede dejar campos en blanco."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, text, duration)
        toast.show()
    }
}

