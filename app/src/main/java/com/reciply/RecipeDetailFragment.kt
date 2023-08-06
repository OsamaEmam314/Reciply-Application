package com.reciply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.reciply.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class RecipeDetailFragment : Fragment() {

lateinit var category:TextView
    lateinit var Ares:TextView
    lateinit var instruction:TextView
    lateinit var video:YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_recipe_detail, container, false)

        return view
    }


}