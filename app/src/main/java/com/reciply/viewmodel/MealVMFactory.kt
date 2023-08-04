package com.reciply.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.network.MealViewModel
import com.reciply.repo.MealsRepository

class MealVMFactory(val mealsRepository: MealsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MealViewModel::class.java)){
            MealViewModel(mealsRepository) as T
        }
        else{
            throw IllegalArgumentException("MealViewModel class not found")
        }
    }
}