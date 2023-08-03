package com.reciply

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.example.reciply.R
import com.reciply.network.ServiceViewModel

@Suppress("DEPRECATION")
class AuthActivity : AppCompatActivity() {
     lateinit var viewModel: ServiceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel= ViewModelProvider(this).get(ServiceViewModel::class.java)
        viewModel.getMealById("a")
        viewModel.getRandomMeal()
    }

    override fun onBackPressed() {
        val navController=findNavController(R.id.AuthNav_Host)
        val currentDestination = navController.currentDestination?.id
        val loginFragment = R.id.loginFragment2
        if(currentDestination==loginFragment)
        {
            finish()
        }
      /*  else{
            super.onBackPressed()
        }*/
    }
}