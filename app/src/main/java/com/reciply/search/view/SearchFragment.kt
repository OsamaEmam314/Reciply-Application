package com.reciply.search.view

import MealViewModel
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reciply.R
import com.google.android.material.textfield.TextInputLayout
import com.reciply.data.models.Meal
import com.reciply.data.network.ApiClient
import com.reciply.repo.MealsRepositoryImpl
import com.reciply.viewmodel.MealVMFactory

class SearchFragment : Fragment() {
    private val TAG  = "SearchFragment"
    lateinit var etSearch: TextInputLayout
    lateinit var recyclerSearch: RecyclerView
    lateinit var searchViewModel: MealViewModel
    lateinit var tvNoResults: TextView

    lateinit var nav_controller: NavController
    lateinit var adapterSearch: SearchRecyclerAdapter
    var listOfMeals: List<Meal> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSearch = view.findViewById(R.id.et_search_src_frg)
        recyclerSearch = view.findViewById(R.id.recycler_search_frg)
        tvNoResults = view.findViewById(R.id.tv_no_results_src_frg)

        // check which controller *********************************
        nav_controller = findNavController()
        adapterSearch = SearchRecyclerAdapter(requireContext(), nav_controller)

        // need to change the call to use factory **************************
//        searchViewModel= ViewModelProvider(this).get(MealViewModel::class.java)
//        getSearchViewModelReady()
        getViewModelReady()

        // set adapter and recycler view
        recyclerSearch.adapter = adapterSearch
        recyclerSearch.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


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
                    tvNoResults.visibility = View.VISIBLE
                }

            }
            override fun afterTextChanged(s: Editable?) {
//                searchForRecipe(s.toString())
            }
        })
    }

    fun searchForRecipe(seq: String){
        // hit api and return results
        searchViewModel.getMealByName(seq)
        Log.d(TAG, "searchForRecipe: passed search")
        searchViewModel.MealListBYNmae.observe(this){
            if (it != null){
                listOfMeals = it
                adapterSearch.setData(listOfMeals)
                tvNoResults.visibility = View.GONE
            }else{
                tvNoResults.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "No Results", Toast.LENGTH_SHORT).show()
                adapterSearch.clearData()
            }
        }

    }

//    private fun getSearchViewModelReady(){
//        val mealsFactory = MealVMFactory(
//            MealsRepositoryImpl(ApiClient, LocalDatabaseImpl(requireContext().applicationContext))
//        )  // send instance of Imp for repo
//        searchViewModel = ViewModelProvider(this, mealsFactory).get(SearchViewModel::class.java)
//    }

    private fun getViewModelReady(){
        //val mealsFcctory = MealVMFactory(MealsRepositoryImpl(ApiClient))
        //searchViewModel = ViewModelProvider(this, mealsFcctory).get(MealViewModel::class.java)
    }

}