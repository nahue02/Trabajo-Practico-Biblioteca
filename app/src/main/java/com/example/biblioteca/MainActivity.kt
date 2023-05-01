package com.example.biblioteca

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var textViewUsuario: TextView
    lateinit var buttonSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //xml
        textViewUsuario = findViewById(R.id.textView_Email)
        buttonSalir = findViewById(R.id.button_Salir)
        val usuario = intent.extras!!.getString("usuario")

        textViewUsuario.text = usuario
        //
        buttonSalir.setOnClickListener{
            salir()
        }

    }

    private fun salir(){
        Firebase.auth.signOut()
        finish()
    }
}