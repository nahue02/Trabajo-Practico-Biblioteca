package com.example.biblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.authentication.LoginActivity
import com.example.biblioteca.databinding.ActivityHomeBinding
import com.example.biblioteca.recyclerview.GeneroAdapter
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = Firebase.auth
        db = FirebaseDatabase.getInstance()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //recyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getGeneros()

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item1 -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                    onPause()
                    true
                }
                R.id.nav_item2 -> {
                    val intent = Intent(this, ConfiguracionActivity::class.java)
                    startActivity(intent)
                    onPause()
                    true
                }
                R.id.nav_item3 -> {
                    FirebaseAuth.getInstance().signOut()
                    irPantallaLogin()
                    finish()
                    true
                }
                else -> false
            }
        }

        val textViewNombre = binding.textViewNombre

        val userId = auth.currentUser?.uid
        val userRef = db.reference.child("usuarios").child(userId!!)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Obtener los datos del perfil del usuario
                val username = snapshot.child("nombre").getValue(String::class.java)
                textViewNombre.text = username
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error en caso de que ocurra
            }
        })
    }

    private fun irPantallaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun onGenerosObtenidos(generos: List<String>) {
        val generosDistinct = generos.distinct().toMutableList()
        generosDistinct.remove("General")
        generosDistinct.add(0, "General")

        runOnUiThread {
            recyclerView.adapter = GeneroAdapter(generosDistinct, this)
        }
    }

    private fun getGeneros() {
        val database = FirebaseDatabase.getInstance().reference.child("libros")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val generos = mutableListOf<String>()

                for (libroSnapshot in snapshot.children) {
                    val genero = libroSnapshot.child("genero").getValue(String::class.java)
                    genero?.let {
                        generos.add(it)
                    }
                }

                generos.distinct().toMutableList().apply {
                    remove("General")
                    add(0, "General")
                }

                onGenerosObtenidos(generos)
            }



            override fun onCancelled(error: DatabaseError) {
                // Manejar el error de lectura de la base de datos si es necesario
            }

        })


    }
}