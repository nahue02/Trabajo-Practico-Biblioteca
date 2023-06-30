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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    //Declara una instancia de FirebaseAuth.
    private lateinit var auth: FirebaseAuth

    //Inicializa una instancia de Cloud Firestore

    private lateinit var db: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        db = FirebaseDatabase.getInstance()


        val actionBar = binding.toolbarWithBackButton
        setSupportActionBar(actionBar)
        supportActionBar?.title = "Registrarse"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val editTextNombre = binding.editTextNombre
        val editTextEmail = binding.editTextEmailRegistro
        val editTextContrasena = binding.editTextContrasenaRegistro

        val buttonRegistrarse = binding.buttonRegistrarse2


        buttonRegistrarse.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val email = editTextEmail.text.toString()
            val contrasena = editTextContrasena.text.toString()

            if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
                mostrarMensaje("No pueden haber espacios vacios")
            } else if (contrasena.length < 6) {
                mostrarMensaje("La contraseña no puede tener menos de 6 caracteres")
            } else {
                registrarUsuario(nombre, email, contrasena)
            }
        }
    }

    private fun registrarUsuario(nombre: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val databaseRef = db.reference.child("usuarios").child(auth.currentUser!!.uid)
                    val usuario = Usuario(auth.currentUser!!.uid, nombre, email, password)

                    databaseRef.setValue(usuario).addOnCompleteListener(){
                        if (it.isSuccessful){
                            mostrarMensaje("Se registró el usuario")
                            startMainActivity(auth.currentUser)
                        }else{
                            Log.w(TAG, "errorGuardarUsuarioEnRealtime", it.exception)
                            mostrarMensaje("Error al guardar usuario en base")
                        }
                    }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    mostrarMensaje("Error al registrar el usuario")
                }
            }
    }


    private fun startMainActivity(user: FirebaseUser?) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun mostrarMensaje(mensaje: String) {
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, mensaje, duration)
        toast.show()
    }

}