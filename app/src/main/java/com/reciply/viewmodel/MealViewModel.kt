package com.reciply.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.Meal
import com.reciply.repo.MealsRepository
import kotlinx.coroutines.launch

class MealViewModel(val mealsRepository: MealsRepository):ViewModel() {
    private val _MealListBYName= MutableLiveData<List<Meal>>()
    val MealListBYNmae: LiveData<List<Meal>> =_MealListBYName
    private val randomMeal=MutableLiveData<Meal?>()

    private val _listOfMealsByLetter=MutableLiveData<List<Meal>>()
    val listOfMealsByLetter:LiveData<List<Meal>> = _listOfMealsByLetter

/*
    fun getMealById(Name:String){
        viewModelScope.launch {
            val response= ApiClient.getMealByName(Name)
            Log.d("asd->", "listofMeals:$response ")
            _MealListBYName.value=response.meals

        }
    }
*/


    fun getRandomMeal(){
        viewModelScope.launch {
            val rand= mealsRepository.getRemoteRandomMeal()
            val randomrecipe=rand.meals.randomOrNull()
            Log.d("random->", "Random Meal:${randomrecipe} ")
            randomMeal.value=randomrecipe

           }
    }

    fun listMealsByLetter(){
        viewModelScope.launch {
            val randLetter = ('A'..'Z').random()

        }
    }
}