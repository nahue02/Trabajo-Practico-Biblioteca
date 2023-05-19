package com.example.biblioteca.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.biblioteca.MainActivity
import com.example.biblioteca.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistroLoginActivity : AppCompatActivity() {
    private lateinit var botonLogin: Button
    private lateinit var botonRegistrarse: Button
    private lateinit var editTextName: TextView
    private lateinit var editTextPassword: TextView

    //Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrologin)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //xml
        botonLogin = findViewById(R.id.button_Login)
        botonRegistrarse = findViewById(R.id.button_Registrarse)

        editTextName = findViewById(R.id.editText_Name)
        editTextPassword = findViewById(R.id.editText_Password)

        //
        botonLogin.setOnClickListener {
            iniciarSesion(editTextName.text.toString(), editTextPassword.text.toString())
        }
        botonRegistrarse.setOnClickListener {
            crearUsuario(editTextName.text.toString(), editTextPassword.text.toString())
        }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            showMainActivity(currentUser)
        }
    }

    private fun iniciarSesion(email: String, password: String) {
        if (verificarNoNullo(email) && verificarNoNullo(password)) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        showMainActivity(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            showAlert()
        }

    }

    private fun crearUsuario(email: String, password: String) {
        if (verificarNoNullo(email) && verificarNoNullo(password)) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        showMainActivity(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }else{
            showAlert()
        }

    }

    private fun showMainActivity(user: FirebaseUser?) {
        val i = Intent(this, MainActivity::class.java).apply {
            putExtra("usuario", user?.email.toString())
        }

        startActivity(i)
    }

    private fun verificarNoNullo(texto: String): Boolean {
        return texto.isNotBlank()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No pueden haber espacios en blanco")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}