package com.reciply

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
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.reciply.R
import com.google.android.material.textfield.TextInputLayout
import com.reciply.data.Meal
import com.reciply.viewmodel.MealViewModel

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

        // check which controller
        nav_controller = findNavController()
        adapterSearch = SearchRecyclerAdapter(requireContext(), nav_controller)

        searchViewModel= ViewModelProvider(this).get(MealViewModel::class.java)

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

}