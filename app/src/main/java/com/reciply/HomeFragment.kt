package com.reciply

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
import com.reciply.repo.MealsRepositoryImpl
import com.reciply.viewmodel.HomeVMFactory


class HomeFragment : Fragment() {
    val data: List<Meal> = arrayListOf(
    )

    private lateinit var rv: RecyclerView
    private lateinit var viewModel: HomeViewModel
    private lateinit var mealAdapter: RecipeItemAdapter
    lateinit var txtRecipeTitle:TextView
    lateinit var txtViewCategory:TextView
    lateinit var imgView:ImageView
    lateinit var shimmerViewContainer: ShimmerFrameLayout
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
        txtRecipeTitle=view.findViewById<TextView>(R.id.txt_meal_name)
        txtViewCategory=view.findViewById<TextView>(R.id.txt_meal_category)
        imgView=view.findViewById<ImageView>(R.id.img_meal)
        shimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

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

        viewModel.listOfMealsByLetter.observe(viewLifecycleOwner){ it ->
            /*while(it==null){
                viewModel.listMealsByLetter()
            }*/
            mealAdapter= RecipeItemAdapter(it)
            rv.adapter=mealAdapter
            mealAdapter.onItemClick={
                val action =HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(it)
                view.findNavController().navigate(action)
            }
            shimmerViewContainer.stopShimmerAnimation();
            shimmerViewContainer.visibility = View.GONE
        }
        rv.layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)

    }

    private fun getViewModelReady() {
        val mealsFactory=HomeVMFactory(MealsRepositoryImpl(ApiClient))
        viewModel= ViewModelProvider(this,mealsFactory)[HomeViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        shimmerViewContainer.startShimmerAnimation()
    }

   override fun onPause() {
        shimmerViewContainer.stopShimmerAnimation()
        super.onPause()
    }

}