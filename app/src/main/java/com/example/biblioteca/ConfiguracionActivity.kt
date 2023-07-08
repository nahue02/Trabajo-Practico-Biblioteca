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

        val actionBar = binding.toolbarWithBackButton
        setSupportActionBar(actionBar)
        supportActionBar?.title = "Editar Usuario"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = Firebase.auth
        db = FirebaseDatabase.getInstance()

        val editTextNombre = binding.editTextNombreConfig
        val editTextEmail = binding.editTextEmailConfig
        val editTextContrasena = binding.editTextContrasenaConfig

        val buttonActualizarNombre = binding.buttonActualizarNombre
        val buttonActualizarEmail = binding.buttonActualizarEmail
        val buttonActualizarContrasena = binding.buttonActualizarContrasena

        val user = auth.currentUser
        val userRef = db.reference.child("usuarios").child(user!!.uid)

        buttonActualizarNombre.setOnClickListener {

            val nombre = editTextNombre.text.toString()

            if (nombre.isEmpty()) {
                mostrarMensaje("Ingrese un nombre")
            } else {
                val profileUpdatesDatabase = hashMapOf<String, Any>(
                    "nombre" to nombre,
                )
                userRef.updateChildren(profileUpdatesDatabase).addOnCompleteListener {
                    if (it.isSuccessful) {
                        mostrarMensaje("Se actualizó su nombre correctamente")
                    } else {
                        mostrarMensaje("Hubo un error de conexión")
                    }
                }

            }
        }

        buttonActualizarEmail.setOnClickListener {

            val email = editTextEmail.text.toString()

            if (email.isEmpty()) {
                mostrarMensaje("Ingrese un email")
            } else {
                val profileUpdatesDatabase = hashMapOf<String, Any>(
                    "email" to email,
                )


                user.updateEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            userRef.updateChildren(profileUpdatesDatabase)
                            Log.d(TAG, "User email address updated.")
                            mostrarMensaje("Se actualizó su email correctamente")
                        } else {
                            mostrarMensaje("Hubo un error de conexion")
                        }
                    }
            }
        }

        buttonActualizarContrasena.setOnClickListener {

            val contrasena = editTextContrasena.text.toString()

            if (contrasena.isEmpty()) {
                mostrarMensaje("Ingrese una contraseña")
            } else if (contrasena.length < 6) {
                mostrarMensaje("La contraseña no puede tener menos de 6 caracteres")
            } else {
                val profileUpdatesDatabase = hashMapOf<String, Any>(
                    "contrasena" to contrasena
                )

                user.updatePassword(contrasena)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            userRef.updateChildren(profileUpdatesDatabase)
                            Log.d(TAG, "User password updated.")
                            mostrarMensaje("Se actualizó su contraseña correctamente")
                        } else {
                            mostrarMensaje("Hubo un error de conexion")
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