package com.reciply.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reciply.search.repo.SearchRepo

class SearchVMFactory(val repo: SearchRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            SearchViewModel(repo) as T

        }else{
            throw IllegalArgumentException("SearchViewModel class not found")
        }
    }
}