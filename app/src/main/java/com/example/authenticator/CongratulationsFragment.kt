package com.example.authenticator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlin.system.exitProcess

class CongratulationsFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_congratulations, container, false)
        val loggedTextView = view.findViewById<TextView>(R.id.loggedTextView)
        val logoutButton = view.findViewById<Button>(R.id.logoutButton)

        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) loggedTextView.text = String.format(getString(R.string.logged_in), firebaseUser.email)
        else Navigation.findNavController(view).navigate(R.id.loginFragment)

        logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            Navigation.findNavController(view).navigate(R.id.loginFragment)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.moveTaskToBack(true)
                exitProcess(-1)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}