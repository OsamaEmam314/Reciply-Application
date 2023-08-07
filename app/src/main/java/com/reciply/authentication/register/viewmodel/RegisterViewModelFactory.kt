package com.reciply.authentication.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.authentication.register.repository.RegisterRepo
class RegisterViewModelFactory(private val registerRepo: RegisterRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(registerRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
