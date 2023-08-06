package com.reciply.search.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.models.Meal
import com.reciply.data.network.ApiClient
import com.reciply.repo.MealsRepository
import com.reciply.search.repo.SearchRepo
import kotlinx.coroutines.launch

class SearchViewModel(var repo: SearchRepo): ViewModel() {

    private val _mealList= MutableLiveData<List<Meal>>()
    val mealList: LiveData<List<Meal>> =_mealList

    fun getMealByName(seq: String){
        viewModelScope.launch {
            val response= repo.getRemoteMealByName(seq)
            _mealList.value = response.meals
        }
    }

    // call the methods that add and delete from the fav list
}