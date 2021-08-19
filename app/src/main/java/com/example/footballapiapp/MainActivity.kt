package com.example.footballapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.footballapiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var nullableBinding: ActivityMainBinding? = null
    private val binding get() = nullableBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nullableBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        APP_ACTIVITY = this
    }
}

lateinit var APP_ACTIVITY: MainActivity

