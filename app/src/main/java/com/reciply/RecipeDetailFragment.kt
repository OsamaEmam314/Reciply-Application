package com.reciply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.reciply.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.reciply.data.models.Meal
import java.util.regex.Pattern


class RecipeDetailFragment : Fragment() {

    lateinit var category: TextView
    lateinit var Ares: TextView
    lateinit var instruction: TextView
    lateinit var video: YouTubePlayerView
    lateinit var image_detail: ImageView
    lateinit var recipy_name: TextView
    lateinit var fav_btn:AppCompatButton
    private val args: RecipeDetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)
        //val bottomNavigationItemView=view.findViewById<BottomNavigationView>(R.id.navigation)
       // bottomNavigationItemView.visibility=View.GONE
      // val view2 = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        activity?.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)!!.visibility=View.GONE
     //   val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
     //   view2.visibility = View.GONE
      //  fab.visibility = View.GONE
        val recipy = args.Meal
        image_detail = view.findViewById(R.id.imageDetails)
        category = view.findViewById(R.id.tv_category)
        Ares = view.findViewById(R.id.tv_area)
        recipy_name = view.findViewById(R.id.tv_recipy_name)
        instruction = view.findViewById(R.id.tv_instruction)
        fav_btn = view.findViewById(R.id.favbtn)
        video = view.findViewById(R.id.youtube_player_view)
        category.text = recipy.strCategory
        Ares.text = recipy.strArea
        recipy_name.text = recipy.strMeal
        instruction.text = recipy.strInstructions
        val video_id = getVideoId(recipy.strYoutube)
       fav_btn.setOnClickListener{
           it.setBackgroundResource(R.drawable.baseline_favorite_24)
       }
        video.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(video_id.toString(), 0F)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state)
            }
        })
        Glide.with(this).load(recipy.strMealThumb).into(image_detail)
        return view
    }

    override fun onPause() {
        super.onPause()
        activity?.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)!!.visibility=View.VISIBLE
    }
}

fun getVideoIdFromUrl(url: String): String? {
    // Regular expression pattern
    val pattern =
        "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200C2F|youtu.be%2F|\\/v%2F|e%2F|watch\\?v=|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|embed%\u200C\u200C2F|youtu.be%2F|%2Fvideos%2F|embed%\u200C\u200C2F|youtu.be%2F|\\/v\\/|e%2F|v%2F)([^#\\&\\?\\n]*)"

    // Create a pattern object
    val compiledPattern = Pattern.compile(pattern)

    // Match the pattern with the provided URL
    val matcher = compiledPattern.matcher(url)

    // Check if a match is found
    return if (matcher.find()) {
        matcher.group()
    } else {
        null
    }
}
fun getVideoId(videoUrl: String): String? {
    if (videoUrl.contains("watch?v=")) {
        val splits = videoUrl.split("watch?v=")
        if (splits.size > 1) {
            return splits[1]
        }
    }
    return null
}