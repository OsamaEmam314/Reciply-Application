package com.reciply.details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.details.repo.DetailsRepo
import com.reciply.search.repo.SearchRepo

class DetailsVMFactory(val repo: DetailsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            DetailsViewModel(repo) as T

        }else{
            throw IllegalArgumentException("DetailsViewModel class not found")
        }
    }
}