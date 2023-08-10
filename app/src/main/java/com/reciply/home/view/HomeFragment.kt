package com.reciply.home.view

import HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.reciply.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.reciply.data.models.Meal
import com.reciply.network.ApiClient
import com.reciply.home.repo.HomeMealsRepositoryImpl
import com.reciply.home.viewmodel.HomeVMFactory


class HomeFragment : Fragment() {
    val data: List<Meal> = arrayListOf(
    )

    private lateinit var rv: RecyclerView
    private lateinit var viewModel: HomeViewModel
    private lateinit var mealAdapter: RecipeItemAdapter
    private lateinit var txtRecipeTitle:TextView
    private lateinit var txtViewCategory:TextView
    private lateinit var imgView:ImageView
    private lateinit var shimmerViewContainerMain: ShimmerFrameLayout
    private lateinit var shimmerViewContainerRV: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)?.show(2,true)
        rv = view.findViewById(R.id.rv_home)
        txtRecipeTitle=view.findViewById(R.id.txt_meal_name)
        txtViewCategory=view.findViewById(R.id.txt_meal_category)
        imgView=view.findViewById(R.id.img_meal_main)
        shimmerViewContainerMain = view.findViewById(R.id.shimmer_view_container)
        shimmerViewContainerRV = view.findViewById(R.id.shimmer_view_container2)
        shimmerViewContainerMain.bringToFront()
        getViewModelReady()
        viewModel.getRandomMeal()
        viewModel.randomMeal.observe(viewLifecycleOwner){randMeal->
            txtRecipeTitle.text=randMeal.strMeal
            txtViewCategory.text=randMeal.strCategory
            Glide.with(view.context)
                .load(randMeal.strMealThumb)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.product_placeolder)
                        .error(R.drawable.baseline_broken_image_24))
                .into(imgView)
            shimmerViewContainerMain.stopShimmerAnimation()
            shimmerViewContainerMain.visibility = View.GONE
            imgView.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(randMeal)
                view.findNavController().navigate(action)
            }
        }

        viewModel.listMealsByLetter()

        viewModel.listOfMealsByLetter.observe(viewLifecycleOwner){ it ->
            mealAdapter= RecipeItemAdapter(it)
            rv.adapter=mealAdapter
            mealAdapter.onItemClick={
                val action = HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(it)
                view.findNavController().navigate(action)
            }
            shimmerViewContainerRV.stopShimmerAnimation()
            shimmerViewContainerRV.visibility = View.GONE
        }
        rv.layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)

    }

    private fun getViewModelReady() {
        val mealsFactory= HomeVMFactory(HomeMealsRepositoryImpl(ApiClient))
        viewModel= ViewModelProvider(this,mealsFactory)[HomeViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        shimmerViewContainerMain.startShimmerAnimation()
        shimmerViewContainerRV.startShimmerAnimation()
    }

   override fun onPause() {
        super.onPause()
       shimmerViewContainerMain.stopShimmerAnimation()
       shimmerViewContainerRV.stopShimmerAnimation()
    }

}