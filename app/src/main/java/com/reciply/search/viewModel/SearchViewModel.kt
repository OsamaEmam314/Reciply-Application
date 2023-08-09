package com.reciply.search.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.models.Meal
import com.reciply.data.models.UserFavList
import com.reciply.search.repo.SearchRepo
import kotlinx.coroutines.launch

class SearchViewModel(var repo: SearchRepo): ViewModel() {
    private val TAG = "SearchViewModel"

    private val _mealList= MutableLiveData<List<Meal>>()
    val mealList: LiveData<List<Meal>> =_mealList

    private val _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> = _isFav

    private val _isExists = MutableLiveData<Boolean>()
    val isExists: LiveData<Boolean> = _isExists

    fun getMealByName(seq: String){
        viewModelScope.launch {
            val response= repo.getRemoteMealByName(seq)
            _mealList.value = response.meals
        }
    }

    fun checkFavRecipe(userId: Int, mealId: String){
        viewModelScope.launch {
            val response = repo.checkFavRecipe(userId, mealId)
            _isFav.value = response
            Log.d(TAG, "checkRecipeExists: response $response")
        }
    }

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

    fun checkRecipeExists(mealID: String){
        viewModelScope.launch {
            var response =  repo.checkRecipeExists(mealID)
            _isExists.value = response
            Log.d(TAG, "checkRecipeExists: response $response")
        }
    }

}