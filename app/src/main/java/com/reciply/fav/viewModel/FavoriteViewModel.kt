package com.reciply.fav.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.models.Meal
import com.reciply.fav.repo.FavRepo
import kotlinx.coroutines.launch

class FavoriteViewModel(var repo: FavRepo): ViewModel() {

    // for getMealById(id: String)
    private val _meal= MutableLiveData<Meal>()
    val mealList: LiveData<Meal> =_meal

    // for getUserFavList(userID: Int)
    private val _mealsFavList= MutableLiveData<List<String>>()
    val mealsFavList: LiveData<List<String>> = _mealsFavList



    fun getMealById(id: String){
        viewModelScope.launch {
            val response= repo.getMealById(id)
            _meal.value = response
        }
    }

    fun getUserFavList(userID: Int){
        viewModelScope.launch {
            val response = repo.getUserFavList(userID)
            _mealsFavList.value = response
        }
    }


    fun deleteFromFavRecipe(userID: Int, mealID: String){
        viewModelScope.launch {
            repo.deleteFromFavRecipe(userID, mealID)
        }
    }
}