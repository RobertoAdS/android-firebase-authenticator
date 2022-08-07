package com.example.authenticator

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val registerButton = view.findViewById<Button>(R.id.confirmRegister)
        val backToLoginButton = view.findViewById<Button>(R.id.backToLogin)
        val emailEditText = view.findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordTextEdit)

        firebaseAuth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (TextUtils.isEmpty(email)) {
                emailEditText.error = "This doesn't look like a valid e-mail"
                emailEditText.requestFocus()
            } else if (TextUtils.isEmpty(password)) {
                passwordEditText.error = "Password cannot be empty"
                passwordEditText.requestFocus()
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, "User registered successfully", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        Toast.makeText(activity, "Failed: " + (it.exception?.message ?: "NULL MESSAGE"), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        backToLoginButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }
}