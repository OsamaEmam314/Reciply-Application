package com.reciply.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.network.MealViewModel
import com.reciply.repo.MealsRepository
import com.reciply.search.SearchViewModel

class MealVMFactory(val mealsRepository: MealsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MealViewModel::class.java)){
            MealViewModel(mealsRepository) as T

        }

//        else if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
//            SearchViewModel(mealsRepository) as T
//        }

        else{
            throw IllegalArgumentException("ViewModel class not found")
        }
    }
}