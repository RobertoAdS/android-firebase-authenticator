package com.example.authenticator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()

        val firebaseUser = firebaseAuth.currentUser
        val navigationController = findNavController(R.id.fragmentContainerView)

        if (firebaseUser == null) {
            navigationController.navigateUp()
            navigationController.navigate(R.id.loginFragment)
        } else {
            navigationController.navigateUp()
            navigationController.navigate(R.id.congratulationsFragment)
        }
    }
}