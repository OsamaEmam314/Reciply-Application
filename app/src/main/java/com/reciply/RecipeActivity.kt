package com.reciply

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.reciply.R

class RecipeActivity : AppCompatActivity() {
    lateinit var bottomNavigation:MeowBottomNavigation
    private lateinit var navController: NavController
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.recipeNavHost) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigation = findViewById(R.id.bottomNavigation)
        bottomNavigation.show(2, true)
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.baseline_favorite_24))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.baseline_home_24))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.baseline_search_24))
        setupBottomNavigation()
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_about->{

                homeToAbout()
            }
            R.id.action_signOut->{
                hometoregister()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun hometoregister() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isUserLoggedin",false)
        editor.apply()

        finish()

        navController.navigate(R.id.action_homeFragment_to_registerFragment)
    }
    private fun homeToAbout() {
        navController.navigate(R.id.action_homeFragment_to_aboutFragment)
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