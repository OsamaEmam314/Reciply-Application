package com.reciply.fav.view

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.reciply.R
import com.google.android.material.textfield.TextInputLayout
import com.reciply.data.data.local.LocalDatabaseImpl
import com.reciply.data.models.Meal
import com.reciply.data.network.ApiClient
import com.reciply.fav.repo.FavRepo
import com.reciply.fav.repo.FavRepoImpl
import com.reciply.fav.viewModel.FavoriteVMFactory
import com.reciply.fav.viewModel.FavoriteViewModel
import com.reciply.search.repo.SearchRepoImpl
import com.reciply.search.view.SearchRecyclerAdapter
import com.reciply.search.viewModel.SearchVMFactory
import com.reciply.search.viewModel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private val TAG  = "FavoriteFragment"
    lateinit var recyclerFav: RecyclerView
    //    var listOfFav: List<Meal> = listOf()
    lateinit var adapterFav: FavRecyclerAdapter

    lateinit var nav_controller: NavController

    lateinit var favoriteViewModel : FavoriteViewModel

    lateinit var tvNoFavRecipes: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private var currentUserId: Int = -1
//    lateinit var editor: SharedPreferences.Editor

    var listOfFavMeals: List<Meal> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)?.show(1,true)

        recyclerFav = view.findViewById(R.id.recycler_fav_frg)
        tvNoFavRecipes = view.findViewById(R.id.tv_no_fav_meals_fav_frg)

        nav_controller = findNavController()
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        currentUserId = sharedPreferences.getInt("userId", -1)
        Log.d("SharedPreferences", "Retrieved userId: $currentUserId")
        getFavoriteViewModelReady()

        adapterFav = FavRecyclerAdapter(requireContext(), nav_controller, favoriteViewModel, currentUserId)
        recyclerFav.adapter = adapterFav
        recyclerFav.layoutManager = GridLayoutManager(requireContext(), 2)
        getAllFavList()

    }

    private fun getFavoriteViewModelReady(){
        val factory = FavoriteVMFactory(
            FavRepoImpl(ApiClient, LocalDatabaseImpl(requireContext().applicationContext))
        )  // send instance of Imp for repo
        favoriteViewModel = ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)
    }

    private fun showCustomDialog(){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnDelete: Button = dialog.findViewById(R.id.btn_delete_dialog)
        val btnCancel: Button = dialog.findViewById(R.id.btn_cancel_dialog)

        btnDelete.setOnClickListener {
            // delete recipe from db
        }
        btnCancel.setOnClickListener {
            // hide the dialog
            dialog.dismiss()
        }
        dialog.show()
    }

    fun getAllFavList(){
        var tempList = mutableListOf<Meal>()
        CoroutineScope(Dispatchers.IO).launch {
            favoriteViewModel.getUserFavList(currentUserId)
        }
        favoriteViewModel.mealsFavList.observe(viewLifecycleOwner){
            // loop on teh list and hit api to get the meal by id
            if (it.size != 0) {
                Log.d(TAG, "getAllFavList: the meals recived from db")
                for (i in 0 until it.size){
                    favoriteViewModel.getMealById(it[i])
                    favoriteViewModel.meal.observe(viewLifecycleOwner){
                        tempList.add(it)
                    }
                }

                listOfFavMeals = tempList
                adapterFav.setData(listOfFavMeals)

            }else{
                Log.d(TAG, "getAllFavList: no meals recived from db")
            }

        }

    }

}