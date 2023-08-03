package com.reciply

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reciply.R
import com.google.android.material.textfield.TextInputLayout


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_register, container, false)
        val submitButton: Button = rootView.findViewById(R.id.btn_submit)
        val emailTextInput = rootView.findViewById<EditText>(R.id.txtInput_Email_register)
        val emailTextLayout = rootView.findViewById<TextInputLayout>(R.id.txtlayout_Email_register)
        val passwordTextInput = rootView.findViewById<EditText>(R.id.txtInput_Password_register)
        val passwordTextLayout = rootView.findViewById<TextInputLayout>(R.id.txtlayout_password_register)
        val userNameTextInput = rootView.findViewById<EditText>(R.id.txtInput_userName_register)
        val userNameTextLayout = rootView.findViewById<TextInputLayout>(R.id.txtlayout_userName_register)
        submitButton.setOnClickListener {
            //getting the values
            val emailValue = emailTextInput.text.toString().trim()
            val passwordValue = passwordTextInput.text.toString()
            val userNameValue = userNameTextInput.text.toString().trim()
            //email
            if (emailValue.isBlank()) {
                emailTextLayout.error = "Please write your email"
            } else if (!isValidEmail(emailValue)) {
                emailTextLayout.error = "Wrong Email Format"
            } else {
                emailTextLayout.error = null
            }
            //password
            if (passwordValue.isBlank()) {
                passwordTextLayout.error = "Please enter a password"
            } else if (!isValidPassword(passwordValue)) {
                passwordTextLayout.error =
                    "Password must have at least 5 characters, one capital letter, one special character, and one number."
            } else {
                passwordTextLayout.error = null
            }
            //username
            if (userNameValue.isBlank()) {
                userNameTextLayout.error = "Please enter a username"
            }
            else if (!isValidUserName(userNameValue)) {
                userNameTextLayout.error =
                    "Username must be at least 5 characters long, start with a letter, and contain no special characters."
            } else {
                userNameTextLayout.error = null
            }
            if (emailTextLayout.error == null && passwordTextLayout.error == null && userNameTextLayout.error == null) {
                /*
                logic to save the data of the user in the database
                */

                val action = RegisterFragmentDirections.actionRegisterFragment2ToLoginFragment2()
                findNavController().navigate(action)
            }
        }

        return rootView
    }
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return emailRegex.matches(email)
    }
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[A-Za-z0-9!@#\$%^&*]{5,}\$")
        return passwordRegex.matches(password)
    }
    private  fun isValidUserName(userName:String): Boolean
    {
        val userNameRegex = Regex("^[a-zA-Z][a-zA-Z0-9]{3,}$")
        return userNameRegex.matches(userName)
    }
}
