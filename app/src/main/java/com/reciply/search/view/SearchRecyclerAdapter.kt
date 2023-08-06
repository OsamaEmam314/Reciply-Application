package com.reciply.search.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reciply.R
import com.reciply.data.data.models.Meal



class SearchRecyclerAdapter(var context: Context, var navController: NavController): RecyclerView.Adapter<SearchRecyclerAdapter.MyViewHolder>() {

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
            .apply(RequestOptions().override(300, 300))
            .into(holder.imgMeal)

        // need to check whether the recipe is fav or not
//        Glide.with(context).load(R.drawable.baseline_favorite_border_24).into(holder.imgFavIcon)
        holder.tvMealName.text = recipesResults[position].strMeal

        holder.favIcon.setOnClickListener {
            // check the status (if the recipe is in fav list or not) from db
        }

        holder.itemView.setOnClickListener {
            // navigate to the details fragment and send the id for this recipe
          /*  val action =
                SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(recipesResults[position].idMeal)
            navController.navigate(action)*/
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
//        var imgFavIcon: ImageView = row.findViewById(R.id.img_fav_icon_src_frg)
        var favIcon : CheckBox = row.findViewById(R.id.img_fav_icon_src_frg)
    }
}