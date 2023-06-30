package com.example.biblioteca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.biblioteca.databinding.ActivityLoginBinding
import com.example.biblioteca.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)



        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textViewNombre = binding.perfilNombre
        val textViewEmail = binding.perfilEmail

        auth = Firebase.auth
        db = FirebaseDatabase.getInstance()

        val userId = auth.currentUser?.uid
        val userRef = db.reference.child("usuarios").child(userId!!)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Obtener los datos del perfil del usuario
                val username = snapshot.child("nombre").getValue(String::class.java)
                val email = snapshot.child("email").getValue(String::class.java)

                // Actualizar la interfaz de usuario con los datos del perfil
                // ...

                // Ejemplo: Mostrar los datos del perfil en los campos de texto
                textViewNombre.text = username
                textViewEmail.text = email
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error en caso de que ocurra
            }
        })


    }
}