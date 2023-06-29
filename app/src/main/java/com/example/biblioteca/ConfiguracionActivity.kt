package com.example.biblioteca

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.biblioteca.databinding.ActivityConfiguracionBinding
import com.example.biblioteca.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ConfiguracionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfiguracionBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        binding = ActivityConfiguracionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextNombre = binding.editTextNombreConfig
        val editTextEmail = binding.editTextEmailConfig
        val editTextContrasena = binding.editTextContrasenaConfig
        val buttonActualizarDatos = binding.buttonActualizarDatos

        auth = Firebase.auth
        db = FirebaseDatabase.getInstance()

        buttonActualizarDatos.setOnClickListener {
            val user = auth.currentUser
            val userRef = db.reference.child("usuarios").child(user!!.uid)

            val nombre = editTextNombre.text.toString()
            val email = editTextEmail.text.toString()
            val contrasena = editTextContrasena.text.toString()

            if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
                mostrarMensaje("No pueden haber espacios vacios")
            } else if (contrasena.length < 6) {
                mostrarMensaje("La contraseña no puede tener menos de 6 caracteres")
            } else {
                val profileUpdatesDatabase = hashMapOf<String, Any>(
                    "nombre" to nombre,
                    "email" to email,
                    "contrasena" to contrasena
                )

                userRef.updateChildren(profileUpdatesDatabase)

                user.updatePassword(contrasena)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User password updated.")
                        }
                    }

                user.updateEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User email address updated.")
                        }
                    }


            }
        }

    }


    private fun mostrarMensaje(mensaje: String) {
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, mensaje, duration)
        toast.show()
    }

}