package com.reciply

import MealViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reciply.R
import com.reciply.data.data.models.Meal
import com.reciply.network.ApiClient
import com.reciply.repo.MealsRepositoryImpl
import com.reciply.viewmodel.MealVMFactory


class HomeFragment : Fragment() {
    val data: List<Meal> = arrayListOf(
    )

    private lateinit var rv: RecyclerView
    private lateinit var viewModel: MealViewModel
    private lateinit var mealAdapter: RecipeItemAdapter
    lateinit var txtRecipeTitle:TextView
    lateinit var txtViewCategory:TextView
    lateinit var imgView:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.rv_home)
        txtRecipeTitle=view.findViewById<TextView>(R.id.txt_meal_name)
        txtViewCategory=view.findViewById<TextView>(R.id.txt_meal_category)
        imgView=view.findViewById<ImageView>(R.id.img_meal)

        getViewModelReady()
        viewModel.getRandomMeal()
        viewModel.randomMeal.observe(viewLifecycleOwner){
            txtRecipeTitle.text=it.strMeal
            txtViewCategory.text=it.strCategory
            Glide.with(view.context)
                .load(it.strMealThumb)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.baseline_image_24)
                        .error(R.drawable.baseline_broken_image_24))
                .into(imgView)
        }

        viewModel.listMealsByLetter()

        viewModel.listOfMealsByLetter.observe(viewLifecycleOwner){
            while(it.isEmpty()){
                viewModel.listMealsByLetter()
            }
            mealAdapter= RecipeItemAdapter(it)
            rv.adapter=mealAdapter
            mealAdapter.onItemClick={
                /*val action =
                    HomeFragmentDirections.actionHomeFragmentToImagesFragment3(it.images.toTypedArray())
                view.findNavController().navigate(action)*/
            }
        }
        rv.layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)
    }

    private fun getViewModelReady() {
        val mealsFactory=MealVMFactory(MealsRepositoryImpl(ApiClient))
        viewModel= ViewModelProvider(this,mealsFactory)[MealViewModel::class.java]
    }

}