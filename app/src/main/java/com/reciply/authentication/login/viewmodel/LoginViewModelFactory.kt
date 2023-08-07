package com.reciply.authentication.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.authentication.login.repository.LoginRepo

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val loginRepo: LoginRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
