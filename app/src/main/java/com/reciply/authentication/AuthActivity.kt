package com.reciply.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.reciply.R

@Suppress("DEPRECATION")
class AuthActivity : AppCompatActivity() {
     //lateinit var viewModel: MealViewModel
     private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModel= ViewModelProvider(this).get(MealViewModel::class.java)
        //viewModel.getMealById("a")
        //viewModel.getRandomMeal()
         val navHostFragment = supportFragmentManager.findFragmentById(R.id.AuthNav_Host) as NavHostFragment
         navController = navHostFragment.navController
    }

    override fun onBackPressed() {
        val loginFragment = R.id.loginFragment2
        if (navController.currentDestination?.id == loginFragment) {
            finish()
        } else {

            navController.navigateUp()
        }
    }
}