package com.reciply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.reciply.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.reciply.data.models.Meal
import java.util.regex.Pattern


class RecipeDetailFragment : Fragment() {

lateinit var category:TextView
    lateinit var Ares:TextView
    lateinit var instruction:TextView
    lateinit var video:YouTubePlayerView
    lateinit var image_detail:ImageView

    private val args : RecipeDetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_recipe_detail, container, false)
      val recipy=args.Meal
        image_detail=view.findViewById(R.id.imageDetails)
        category=view.findViewById(R.id.tv_category)
        Ares=view.findViewById(R.id.tv_area)
        instruction=view.findViewById(R.id.tv_instruction)
        video=view.findViewById(R.id.youtube_player_view)
      category.text=recipy.strCategory
       Ares.text=recipy.strArea
        instruction.text=recipy.strInstructions
       val video_id= getVideoIdFromUrl(recipy.strYoutube)
video.addYouTubePlayerListener(object :AbstractYouTubePlayerListener(){

    override fun onReady(youTubePlayer: YouTubePlayer) {
        youTubePlayer.loadVideo(video_id.toString(), 0F)
    }
    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        // this method is called if video has ended,
        super.onStateChange(youTubePlayer, state)
    }
})
        Glide.with(this).load(recipy.strMealThumb).into(image_detail)
        return view
    }


}
fun getVideoIdFromUrl(url: String): String? {
    // Regular expression pattern
    val pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200C2F|youtu.be%2F|\\/v%2F|e%2F|watch\\?v=|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|embed%\u200C\u200C2F|youtu.be%2F|%2Fvideos%2F|embed%\u200C\u200C2F|youtu.be%2F|\\/v\\/|e%2F|v%2F)([^#\\&\\?\\n]*)"

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