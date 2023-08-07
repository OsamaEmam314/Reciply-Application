package com.reciply.fav.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reciply.R
import com.reciply.data.models.Meal
import com.reciply.fav.viewModel.FavoriteViewModel
import com.reciply.search.view.SearchRecyclerAdapter

class FavRecyclerAdapter(var context: Context,
                         var navController: NavController,
                         var viewModel: FavoriteViewModel,
                         var userId: Int)
    : RecyclerView.Adapter<FavRecyclerAdapter.MyViewHolder>()  {

    private var favRecipes: List<Meal> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.fav_recycler_item, parent, false)
        return FavRecyclerAdapter.MyViewHolder(row)
    }

    override fun getItemCount(): Int {
        return favRecipes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(favRecipes[position].strMealThumb)
            .placeholder(R.drawable.product_placeolder)
            .apply(RequestOptions().override(300, 300))
            .into(holder.imgMeal)

        holder.tvMealName.text = favRecipes[position].strMeal

        holder.deleteIcon.setOnClickListener {
            // show the dialog
            showCustomDialog(position)
        }

        holder.itemView.setOnClickListener {
            // navigate to the details fragment and send recipe
            /*  val action =
                  SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(recipesResults[position].idMeal)
              navController.navigate(action)*/
        }
    }

    private fun showCustomDialog(position: Int){
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnDelete: Button = dialog.findViewById(R.id.btn_delete_dialog)
        val btnCancel: Button = dialog.findViewById(R.id.btn_cancel_dialog)

        btnDelete.setOnClickListener {
            // delete recipe from db
            viewModel.deleteFromFavRecipe(userId, favRecipes[position].idMeal)
        }
        btnCancel.setOnClickListener {
            // hide the dialog
            dialog.dismiss()
        }
        dialog.show()
    }

    class MyViewHolder(row: View): RecyclerView.ViewHolder(row) {
        var imgMeal: ImageView = row.findViewById(R.id.img_meal_fav_frg)
        var tvMealName: TextView = row.findViewById(R.id.tv_meal_name_fav_frg)
        var deleteIcon : CheckBox = row.findViewById(R.id.img_delete_fav_frg)
    }

}