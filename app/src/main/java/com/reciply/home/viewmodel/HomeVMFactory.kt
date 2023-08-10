package com.reciply.home.viewmodel

import HomeViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.home.repo.HomeMealsRepository

class HomeVMFactory(val mealsRepository: HomeMealsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            HomeViewModel(mealsRepository) as T

        }

        else{
            throw IllegalArgumentException("ViewModel class not found")
        }
    }
}