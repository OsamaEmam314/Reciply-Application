package com.reciply.aboutUs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reciply.R
import com.reciply.data.models.Person

class AboutUsRecyclerAdapter(var context : Context, var list: List<Person>): RecyclerView.Adapter<AboutUsRecyclerAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.about_us_item, parent, false)
        return MyViewHolder(row)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(list[position].icon)
            .placeholder(R.drawable.product_placeolder)
            .into(holder.imgPerson)

        holder.tvName.text = list[position].name
        holder.tvGithub.text = list[position].github
    }

    class MyViewHolder (row: View): RecyclerView.ViewHolder(row) {
        var imgPerson: ImageView = row.findViewById(R.id.img_person)
        var tvName: TextView = row.findViewById(R.id.tv_name)
        var tvGithub: TextView = row.findViewById(R.id.tv_github)
    }
}