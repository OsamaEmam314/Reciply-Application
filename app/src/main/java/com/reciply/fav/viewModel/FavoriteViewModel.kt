package com.reciply.fav.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.models.Meal
import com.reciply.data.models.Recipe
import com.reciply.data.models.UserFavList
import com.reciply.db.UserWithFavRecipes
import com.reciply.fav.repo.FavRepo
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteViewModel(var repo: FavRepo): ViewModel() {

    private val _mealsFavList= MutableLiveData<UserWithFavRecipes>()
    val mealsFavList: LiveData<UserWithFavRecipes> = _mealsFavList

    fun getRecipesWithUser(userId: Int){
        viewModelScope.launch {
            val response = repo.getRecipesWithUser(userId)
            _mealsFavList.value = response
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

















//    // for getMealById(id: String)
//    private val _meal= MutableLiveData<Meal>()
//    val meal: LiveData<Meal> =_meal
//
//    // for getUserFavList(userID: Int)
//    private val _mealsFavList= MutableLiveData<List<String>>()
//    val mealsFavList: LiveData<List<String>> = _mealsFavList
//
//    private val _meals= MutableLiveData<List<Meal>>()
//    val meals: LiveData<List<Meal>> =_meals
//
//    lateinit var results : Deferred<Any>


//    fun getMealById(id: String){
//        viewModelScope.launch {
//            val response= repo.getMealById(id)
//            _meal.value = response
//        }
//    }


//    fun getUserFavList(userID: Int){
////        viewModelScope.launch {
////            val response = repo.getUserFavList(userID)
////            _mealsFavList.value = response
////        }
//        results = viewModelScope.async {
//            val response = repo.getUserFavList(userID)
//            _mealsFavList.value = response
//        }
//    }


//    fun deleteFromFavRecipe(userID: Int, mealID: String){
//        viewModelScope.launch {
//            repo.deleteFromFavRecipe(userID, mealID)
//        }
//    }
//
//    fun getMeals(list: List<String>){
//        var tempList = mutableListOf<Meal>()
//        viewModelScope.launch {
//            results.await()
//            Log.d("FavViewModel==", "getMeals: ${list.size} and IDs : $list")
//            for(i in 0 until list.size){
//                Log.d("FavViewModel==", "getMeals:***** ${repo.getMealById(list[i])}")
//                tempList.add(repo.getMealById(list[i]))
////                Log.d("FavViewModel==", "getMeals: ${tempList[i].idMeal}")
//            }
//            _meals.value = tempList
//            Log.d("FavViewModel==", "getMeals: ${tempList.size} and meals $tempList")
//        }
//
//    }
}