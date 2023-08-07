package com.reciply.viewmodel

import HomeViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.repo.MealsRepository

class HomeVMFactory(val mealsRepository: MealsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            HomeViewModel(mealsRepository) as T

        }

//        else if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
//            SearchViewModel(mealsRepository) as T
//        }

        else{
            throw IllegalArgumentException("ViewModel class not found")
        }
    }
}