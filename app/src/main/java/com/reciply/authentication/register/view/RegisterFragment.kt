package com.reciply.authentication.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.reciply.R
import com.google.android.material.textfield.TextInputLayout
import com.reciply.authentication.register.repository.RegisterRepoImpl
import com.reciply.authentication.register.viewmodel.RegisterViewModel
import com.reciply.authentication.register.viewmodel.RegisterViewModelFactory
import com.reciply.data.data.local.LocalDatabaseImpl
import com.reciply.data.models.User
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

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

        // Initialize RegisterViewModel using the RegisterViewModelFactory
        val localDatabase = LocalDatabaseImpl(requireContext()) // Initialize your LocalDatabaseImpl with the appropriate context
        val registerRepo = RegisterRepoImpl(localDatabase)
        val registerViewModelFactory = RegisterViewModelFactory(registerRepo)
        registerViewModel = ViewModelProvider(this, registerViewModelFactory).get(RegisterViewModel::class.java)

        submitButton.setOnClickListener {
            // Getting the values
            val emailValue = emailTextInput.text.toString().trim()
            val passwordValue = passwordTextInput.text.toString()
            val userNameValue = userNameTextInput.text.toString().trim()

            // Validate email, password, and username
            val isEmailValid = isValidEmail(emailValue)
            val isPasswordValid = isValidPassword(passwordValue)
            val isUserNameValid = isValidUserName(userNameValue)

            // Update error messages if necessary
            emailTextLayout.error = if (!isEmailValid) "Wrong Email Format" else null
            passwordTextLayout.error = if (!isPasswordValid) "Password must have at least 5 characters, one capital letter, one special character, and one number." else null
            userNameTextLayout.error = if (!isUserNameValid) "Username must be at least 5 characters long, start with a letter, and contain no special characters." else null

            // If all fields are valid, register the new user
            if (isEmailValid && isPasswordValid && isUserNameValid) {
                // Use lifecycleScope to launch a coroutine inside the fragment
                // We can call suspend function registerNewUser from a coroutine.
                lifecycleScope.launch {
                    val isUserRegistered = registerViewModel.registerNewUser(emailValue, passwordValue, userNameValue)

                    if (isUserRegistered) {
                        // User registered successfully, you can display a success message or navigate to another fragment
                        val action = RegisterFragmentDirections.actionRegisterFragment2ToLoginFragment2()
                        findNavController().navigate(action)
                    } else {

                        emailTextLayout.error = "User with the same email already exists."
                    }
                }
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

    private fun isValidUserName(userName: String): Boolean {
        val userNameRegex = Regex("^[a-zA-Z][a-zA-Z0-9]{3,}$")
        return userNameRegex.matches(userName)
    }
}
