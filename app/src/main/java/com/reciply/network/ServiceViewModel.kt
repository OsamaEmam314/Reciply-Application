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
    private val _MealBYid= MutableLiveData<Meal>()
    val MealBYid: LiveData<Meal> =  _MealBYid
    fun getMealById(){
        viewModelScope.launch {
            val response=ApiClient.getMealById("52772")
            Log.d("asd->", "getMealById:${response.strArea} ")

        }
    }
}