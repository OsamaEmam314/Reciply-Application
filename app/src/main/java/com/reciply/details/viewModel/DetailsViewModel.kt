package com.reciply.details.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.models.Meal
import com.reciply.data.models.UserFavList
import com.reciply.details.repo.DetailsRepo
import com.reciply.search.repo.SearchRepo
import kotlinx.coroutines.launch

class DetailsViewModel(var repo: DetailsRepo): ViewModel() {
    private val TAG = "DetailsViewModel"

    private val _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> = _isFav

    fun insertIntoFavList(userFavList: UserFavList){
        viewModelScope.launch {
            repo.insertIntoFavRecipe(userFavList)
        }
    }
    fun insertRecipe(recipe: Meal){
        viewModelScope.launch {
            repo.insertRecipe(recipe)
        }
    }

    fun deleteFromFavList(userFavList: UserFavList){
        viewModelScope.launch {
            repo.deleteFromFavRecipes(userFavList)
        }
    }

    fun deleteRecipe(recipe: Meal){
        viewModelScope.launch {
            repo.deleteRecipe(recipe)
        }
    }

    fun checkFavRecipe(userId: Int, mealID: String){
        viewModelScope.launch {
            var response =  repo.checkFavRecipe(userId, mealID)
            _isFav.value = response
            Log.d(TAG, "checkRecipeExists: response $response")
        }
    }
}