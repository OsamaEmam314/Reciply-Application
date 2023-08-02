package com.reciply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reciply.R
import com.reciply.models.Recipe


class HomeFragment : Fragment() {
    val data: List<Recipe> = arrayListOf(
    )

    private lateinit var rv: RecyclerView
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
        rv.adapter= RecipeItemAdapter(data)
        rv.layoutManager = LinearLayoutManager(activity?.applicationContext, RecyclerView.HORIZONTAL, false)

    }
}