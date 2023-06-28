package com.example.biblioteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biblioteca.databinding.ActivityConfiguracionBinding
import com.example.biblioteca.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
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

        val user = auth.currentUser
        val userRef = db.reference.child("users").child(user!!.uid)

        buttonActualizarDatos.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val email = editTextEmail.text.toString()
            val contrasena = editTextContrasena.text.toString()

            actualizarUsuarioAuthentication(user, email, contrasena)
            actualizarUsuarioDatabase(userRef, nombre, email)
        }


    }

    private fun actualizarUsuarioDatabase(
        userRef: FirebaseDatabase,
        newName: String,
        newEmail: String
    ) {

        val profileUpdatesDatabase = hashMapOf<String, Any>(
            "username" to newName,
        )

        userRef.updateChildren(profileUpdatesDatabase)
    }


    private fun actualizarUsuarioAuthentication(
        user: FirebaseUser?,
        newEmail: String,
        newPassword: String
    ) {
        editarEmail(user, newEmail)
        editarContrasena(user, newPassword)
    }

    private fun editarEmail(user: FirebaseUser?, newEmail: String) {
        user?.updateEmail(newEmail)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Actualización del email exitosa
                } else {
                    // Error al actualizar el email
                }
            }
    }

    private fun editarContrasena(user: FirebaseUser?, newPassword: String) {
        user?.updatePassword(newPassword)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Actualización de la contraseña exitosa
                } else {
                    // Error al actualizar la contraseña
                }
            }
    }
}