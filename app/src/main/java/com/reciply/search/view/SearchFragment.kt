package com.reciply.search.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reciply.R
import com.google.android.material.textfield.TextInputLayout
import com.reciply.data.data.local.LocalDatabaseImpl
import com.reciply.data.models.Meal
import com.reciply.data.network.ApiClient
import com.reciply.repo.MealsRepositoryImpl
import com.reciply.search.repo.SearchRepo
import com.reciply.search.repo.SearchRepoImpl
import com.reciply.search.viewModel.SearchVMFactory
import com.reciply.search.viewModel.SearchViewModel

class SearchFragment : Fragment() {
    private val TAG  = "SearchFragment"
    lateinit var etSearch: TextInputLayout
    lateinit var recyclerSearch: RecyclerView
    lateinit var searchViewModel: SearchViewModel
    lateinit var tvNoResults: TextView

    lateinit var nav_controller: NavController
    lateinit var adapterSearch: SearchRecyclerAdapter
    var listOfMeals: List<Meal> = listOf()

    private lateinit var sharedPreferences: SharedPreferences
    var searchKey : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("search_results", Context.MODE_PRIVATE)
        searchKey = sharedPreferences.getString("key_search", "")
        Log.d(TAG, "onCreateView: $searchKey")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSearch = view.findViewById(R.id.et_search_src_frg)
        recyclerSearch = view.findViewById(R.id.recycler_search_frg)
        tvNoResults = view.findViewById(R.id.tv_no_results_src_frg)


        nav_controller = findNavController()
        adapterSearch = SearchRecyclerAdapter(requireContext(), nav_controller)

        getSearchViewModelReady()

        sharedPreferences = requireActivity().getSharedPreferences("search_results", Context.MODE_PRIVATE)

        // set adapter and recycler view
        recyclerSearch.adapter = adapterSearch
        recyclerSearch.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        etSearch.setOnClickListener {
            tvNoResults.visibility = View.GONE
        }

        if(!searchKey.isNullOrEmpty()){
            // retrieve the last search results
            searchForRecipe(searchKey!!)
            // make shared pref empty
            with(sharedPreferences.edit()) {
                putString("key_search", "")
                    commit()
            }

        }

        etSearch.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s?.toString()
                if (!etSearch.editText?.text.isNullOrEmpty()) {
                    searchForRecipe(searchText ?: "")
                    tvNoResults.visibility = View.GONE
                } else {
                    adapterSearch.clearData()
                    tvNoResults.text = "No results found"
                    tvNoResults.visibility = View.VISIBLE
                }

            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun searchForRecipe(seq: String){
        // hit api and return results
        searchViewModel.getMealByName(seq)
        searchViewModel.mealList.observe(viewLifecycleOwner){
            if (it != null){
                tvNoResults.visibility = View.GONE
//                listOfMeals = it
//                adapterSearch.setData(listOfMeals)
                adapterSearch.setData(it)
            }else{
                tvNoResults.text = "No results found"
                tvNoResults.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "No Results", Toast.LENGTH_SHORT).show()
                adapterSearch.clearData()
            }
        }
    }

    private fun getSearchViewModelReady(){
        val mealsFactory = SearchVMFactory(
            SearchRepoImpl(ApiClient, LocalDatabaseImpl(requireContext().applicationContext))
        )  // send instance of Imp for repo
        searchViewModel = ViewModelProvider(this, mealsFactory).get(SearchViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("key_search", etSearch.editText?.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        with(sharedPreferences.edit()) {
            if (!etSearch.editText?.text.isNullOrEmpty()){
                putString("key_search", etSearch.editText?.text.toString())
                    commit()
            }else{
                Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun isFavRecipe(mealId: String): Boolean{
        var listOfFav = listOf<String>()  // need to call a function that get all the fav list from db
         // check if this meal is found in fav meals list from db
        for (i in 0 until listOfFav.size){
            if(mealId == listOfFav[i]){
                return true
            }
        }
        return false
    }

}