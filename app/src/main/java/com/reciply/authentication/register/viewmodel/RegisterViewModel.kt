package com.reciply.authentication.register.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.authentication.register.repository.RegisterRepo
import com.reciply.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val registerRepo: RegisterRepo) : ViewModel() {
    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> get() = _userLiveData

    fun insertUser(user: User) {
        viewModelScope.launch {
            registerRepo.insertUser(user)
        }
    }

    private suspend fun getUserByEmail(userEmail: String): User? {
        return withContext(Dispatchers.IO) {
            registerRepo.getUserByEmail(userEmail) // Return the User object directly
        }
    }

    suspend fun registerNewUser(email: String, password: String, userName: String): Boolean {
        val existingUser = getUserByEmail(email)
        if (existingUser == null) {
            val user = User(username = userName, password = password, email = email)
            insertUser(user)
            Log.d("asd->", "New User: $user")
            return true // Registration successful
        } else {
            Log.d("asd->", "User already exists: $existingUser")
            return false // User with the same email already exists, registration failed
        }
    }
}
