package com.reciply.fav.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.fav.repo.FavRepo
import com.reciply.search.viewModel.SearchViewModel

class FavoriteVMFactory(val repo: FavRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            FavoriteViewModel(repo) as T

        }else{
            throw IllegalArgumentException("FavoriteViewModel class not found")
        }
    }
}