package com.reciply.authentication.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.reciply.R
import com.google.android.material.textfield.TextInputLayout
import com.reciply.RecipeActivity
import com.reciply.authentication.login.repository.LoginRepoImpl
import com.reciply.authentication.login.viewmodel.LoginViewModel
import com.reciply.authentication.login.viewmodel.LoginViewModelFactory
import com.reciply.data.data.local.LocalDatabaseImpl
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)
        setupViews(rootView)
        setupViewModel()
        return rootView
    }

    private fun setupViews(rootView: View) {
        val goToRegistration = rootView.findViewById<TextView>(R.id.txt_clickToRegister_Login)
        val loginButton = rootView.findViewById<Button>(R.id.btn_login)
        val emailTextLayout = rootView.findViewById<TextInputLayout>(R.id.txtlayout_Email_login)
        val passwordTextLayout = rootView.findViewById<TextInputLayout>(R.id.txtlayout_password_login)
        val emailTextInput = rootView.findViewById<EditText>(R.id.txtInput_Email_login)
        val passwordTextInput = rootView.findViewById<EditText>(R.id.txtInput_Password_Login)

        goToRegistration.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragment2ToRegisterFragment2()
            findNavController().navigate(action)
        }

        loginButton.setOnClickListener {
            validateAndPerformLogin(emailTextInput.text.toString().trim(), passwordTextInput.text.toString(), emailTextLayout, passwordTextLayout)
        }
    }

    private fun setupViewModel() {
        val localDatabase = LocalDatabaseImpl(requireContext())
        val loginRepo = LoginRepoImpl(localDatabase)
        val loginViewModelFactory = LoginViewModelFactory(loginRepo)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    private fun validateAndPerformLogin(email: String, password: String, emailLayout: TextInputLayout, passwordLayout: TextInputLayout) {
        if (email.isEmpty()) {
            emailLayout.error = "Email cannot be empty"
        } else {
            emailLayout.error = null
        }

        if (password.isEmpty()) {
            passwordLayout.error = "Password cannot be empty"
        } else {
            passwordLayout.error = null
        }

        if (passwordLayout.error == null && emailLayout.error == null) {
            lifecycleScope.launch {
                val userAccount = loginViewModel.loggingIn(email, password)
                if (userAccount != null) {
                    if (userAccount.password == password) {
                        saveUserIdToSharedPreferences(userAccount.idUser)
                        val intent = Intent(requireContext(), RecipeActivity::class.java)
                        startActivity(intent)
                    } else {
                        emailLayout.error = "Wrong email or password"
                    }
                } else {
                    emailLayout.error = "There is no account with such an email, please create an account first"
                }
            }
        }
    }

    private fun saveUserIdToSharedPreferences(userId: Int?) {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("userId", userId ?: -1)
        editor.putBoolean("isUserLoggedIn", true)
        editor.apply()

        if (userId != null) {
            Log.d("SharedPreferences", "Saved userId: $userId")
        }
    }
}
