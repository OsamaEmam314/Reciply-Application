package com.reciply.authentication.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
        val goToRegisteration :TextView = rootView.findViewById(R.id.txt_clickToRegister_Login)
        val loginButton : Button = rootView.findViewById(R.id.btn_login)
        goToRegisteration.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragment2ToRegisterFragment2()
            findNavController().navigate(action)
        }
        val emailTextLayout = rootView.findViewById<TextInputLayout>(R.id.txtlayout_Email_login)
        val passwordTextLayout = rootView.findViewById<TextInputLayout>(R.id.txtlayout_password_login)
        val emailTextInput = rootView.findViewById<EditText>(R.id.txtInput_Email_login)
        val passwordTextInput = rootView.findViewById<EditText>(R.id.txtInput_Password_Login)
        val localDatabase = LocalDatabaseImpl(requireContext())
        val loginRepo = LoginRepoImpl(localDatabase)
        val loginViewModelFactory = LoginViewModelFactory(loginRepo)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
        loginButton.setOnClickListener {
            val emailValue = emailTextInput.text.toString().trim()
            val passwordValue = passwordTextInput.text.toString()
            if (emailValue.isEmpty()) {
                emailTextLayout.error = "Email cannot be empty"
            } else {
                emailTextLayout.error = null
            }

            if (passwordValue.isEmpty()) {
                passwordTextLayout.error = "Password cannot be empty"
            } else {
                passwordTextLayout.error = null
            }
            if(passwordTextLayout.error == null && emailTextLayout.error == null)
            {
                lifecycleScope.launch{
                    val userAccount = loginViewModel.loggingIn(emailValue,passwordValue)
                    if(userAccount!=null){
                        if(userAccount.password==passwordValue) {
                            saveUserIdToSharedPreferences(userAccount.idUser)
                            val intent = Intent(requireContext(), RecipeActivity::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            emailTextLayout.error = "Wrong email or password"
                        }
                    }
                    else
                    {
                        emailTextLayout.error = "There is no account with such an email please create an account first"
                    }
                }

            }
        }

        return rootView
    }

    private fun saveUserIdToSharedPreferences(userId: Int?) {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("userId", userId ?: -1)
        editor.putBoolean("isUserLoggedin",true)
        editor.apply()
    }
}