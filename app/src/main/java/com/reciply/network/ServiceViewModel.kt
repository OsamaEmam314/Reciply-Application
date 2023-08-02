package com.reciply.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.Meal
import com.reciply.network.ApiClient
import kotlinx.coroutines.launch

class ServiceViewModel:ViewModel() {
    private val _MealListBYName= MutableLiveData<List<Meal>>()
    val MealListBYNmae: LiveData<List<Meal>> =_MealListBYName
    fun getMealById(Name:String){
        viewModelScope.launch {
            val response=ApiClient.getMealByName(Name)
            Log.d("asd->", "listofMeals:$response ")
            _MealListBYName.value=response.meals

        }
    }
}