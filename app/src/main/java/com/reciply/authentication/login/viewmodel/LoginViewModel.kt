package com.reciply.authentication.login.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.lifecycle.ViewModel
import com.reciply.authentication.login.repository.LoginRepo
import com.reciply.authentication.register.repository.RegisterRepo
import com.reciply.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(private val loginRepo: LoginRepo) : ViewModel()  {

    private suspend fun getUserByEmail(userEmail: String): User? {
        return withContext(Dispatchers.IO) {
            loginRepo.getUserByEmail(userEmail) // Return the User object directly
        }
    }
    suspend fun loggingIn (email: String , password : String) : User? {
        val existingUser = getUserByEmail(email)
        if (existingUser != null)
        {
            Log.d("asd->",  "UserID: ${existingUser.idUser} user is ${existingUser}")
         return existingUser
        }
        else
            return null
    }
}