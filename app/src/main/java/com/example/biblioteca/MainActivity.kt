package com.example.biblioteca

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.biblioteca.authentication.LoginActivity
import com.example.biblioteca.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser != null) {
            irPantallaHome()
            finish()
        }else{
            irPantallaLogin()
            finish()
        }
    }

    private fun irPantallaHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun irPantallaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}


