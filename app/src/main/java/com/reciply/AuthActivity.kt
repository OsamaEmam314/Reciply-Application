package com.reciply

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.reciply.R
import com.reciply.viewmodel.MealViewModel

@Suppress("DEPRECATION")
class AuthActivity : AppCompatActivity() {
     //lateinit var viewModel: MealViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModel= ViewModelProvider(this).get(MealViewModel::class.java)
        //viewModel.getMealById("a")
        //viewModel.getRandomMeal()
    }

    override fun onBackPressed() {
        val navController=findNavController(R.id.AuthNav_Host)
        val currentDestination = navController.currentDestination?.id
        val loginFragment = R.id.loginFragment2
        if(currentDestination==loginFragment)
        {
            finish()
        }
        else{
            super.onBackPressed()
        }
    }
}