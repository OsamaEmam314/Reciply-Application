package com.reciply

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.reciply.R

class RecipeActivity : AppCompatActivity() {
    lateinit var bottomNavigation:MeowBottomNavigation
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.recipeNavHost) as NavHostFragment
        navController = navHostFragment.navController


        bottomNavigation=findViewById(R.id.bottomNavigation)
        bottomNavigation.show(2,true)
        bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.baseline_favorite_24))
        bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.baseline_home_24))
        bottomNavigation.add(MeowBottomNavigation.Model(3,R.drawable.baseline_search_24))
        setupBottomNavigation()

    }

    private fun setupBottomNavigation(){
        bottomNavigation.setOnClickMenuListener {
                when(it.id){
                    1->navController.navigate(R.id.favoriteFragment)
                    2->navController.navigate(R.id.homeFragment)
                    3->navController.navigate(R.id.searchFragment)
                }



        }
    }
}