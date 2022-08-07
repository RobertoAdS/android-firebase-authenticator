package com.example.authenticator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

class CongratulationsFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_congratulations, container, false)
        val loggedTextView = view.findViewById<TextView>(R.id.loggedTextView)

        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) loggedTextView.text = String.format(getString(R.string.logged_in), firebaseUser.email)
        else Navigation.findNavController(view).navigate(R.id.loginFragment)

        return view
    }
}