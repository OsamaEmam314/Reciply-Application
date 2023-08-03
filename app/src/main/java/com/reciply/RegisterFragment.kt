package com.reciply

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reciply.R


class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_register, container, false)
        val submitButton : Button = rootView.findViewById(R.id.btn_submit)
        submitButton.setOnClickListener {
         /*
            logic to save the data of the user in the database


            */
            val action = RegisterFragmentDirections.actionRegisterFragment2ToLoginFragment2()
            findNavController().navigate(action)
        }
        return rootView
    }

}