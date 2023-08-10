package com.reciply.search.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reciply.R
import com.reciply.data.models.Meal
import com.reciply.data.models.Recipe
import com.reciply.data.models.UserFavList
import com.reciply.db.UserWithFavRecipes
import com.reciply.search.viewModel.SearchViewModel


class SearchRecyclerAdapter(var context: Context, var navController: NavController, var viewModel: SearchViewModel, var userId: Int, var owner: LifecycleOwner): RecyclerView.Adapter<SearchRecyclerAdapter.MyViewHolder>() {

    private var recipesResults: List<Meal> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.search_recycler_item, parent, false)
        return MyViewHolder(row)
    }

    override fun getItemCount(): Int {
        return recipesResults.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(recipesResults[position].strMealThumb)
            .placeholder(R.drawable.product_placeolder)
            .into(holder.imgMeal)

        holder.tvMealName.text = recipesResults[position].strMeal
        holder.tvCategory.text = recipesResults[position].strCategory

        holder.itemView.setOnClickListener {
           val action =
                SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(recipesResults[position])
            navController.navigate(action)
        }
    }

    fun setData(listOfMeals: List<Meal>){
        recipesResults = listOfMeals
        notifyDataSetChanged()
    }

    fun clearData(){
        recipesResults = listOf()
        notifyDataSetChanged()
}

    class MyViewHolder(row: View): RecyclerView.ViewHolder(row) {
        var imgMeal: ImageView = row.findViewById(R.id.img_meal_src_frg)
        var tvMealName: TextView = row.findViewById(R.id.tv_meal_name_src_frg)
//        var favIcon : CheckBox = row.findViewById(R.id.img_fav_icon_src_frg)
        var tvCategory: TextView = row.findViewById(R.id.tv_meal_category_src_frg)
    }
}