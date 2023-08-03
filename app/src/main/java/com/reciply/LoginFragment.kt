package com.reciply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.reciply.R


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_login, container, false)
        val goToRegisteration :TextView = rootView.findViewById(R.id.txt_clickToRegister_Login)
        goToRegisteration.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragment2ToRegisterFragment2()
            findNavController().navigate(action)
        }
        return rootView
    }

}