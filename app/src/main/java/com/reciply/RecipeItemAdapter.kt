package com.reciply

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reciply.R
import com.reciply.data.Meal

class RecipeItemAdapter(val data:List<Meal>) : RecyclerView.Adapter<RecipeItemAdapter.MyViewHolder>() {
    private lateinit var context: Context
    var onItemClick: ((Meal)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row= LayoutInflater.from(parent.context).inflate(R.layout.home_recipe_item,parent,false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtRecipeTitle.text=data[position].strMeal
        //holder.txtViewDescription.text=data[position].description
        Glide.with(context)
            .load(data[position].strMealThumb)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.baseline_image_24)
                .error(R.drawable.baseline_broken_image_24))
            .into(holder.imgView)
        holder.itemView.setOnClickListener {

            onItemClick?.invoke(data[position])
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(row: View): RecyclerView.ViewHolder(row){
        var txtRecipeTitle=row.findViewById<TextView>(R.id.txt_rec_title)
        //var txtViewDescription=row.findViewById<TextView>(R.id.txtViewDescription)
        var imgView=row.findViewById<ImageView>(R.id.img_rec_thumb)

    }



    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
}