package com.reciply.details.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.reciply.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.reciply.data.data.local.LocalDatabaseImpl
import com.reciply.data.models.Meal
import com.reciply.data.models.UserFavList
import com.reciply.data.network.ApiClient
import com.reciply.details.repo.DetailsRepoImpl
import com.reciply.details.viewModel.DetailsVMFactory
import com.reciply.details.viewModel.DetailsViewModel
import com.reciply.search.repo.SearchRepoImpl
import com.reciply.search.viewModel.SearchVMFactory
import com.reciply.search.viewModel.SearchViewModel
//import com.reciply.RecipeDetailFragmentArgs
import java.util.regex.Pattern


class RecipeDetailFragment : Fragment() {

    lateinit var category: TextView
    lateinit var Ares: TextView
    lateinit var instruction: TextView
    lateinit var video: YouTubePlayerView
    lateinit var image_detail: ImageView
    lateinit var recipy_name: TextView
//    lateinit var fav_btn:AppCompatButton
    lateinit var favCheckBox: CheckBox
    private val args: RecipeDetailFragmentArgs by navArgs()

    private lateinit var sharedPreferences: SharedPreferences
    private var currentUserId: Int = -1
    lateinit var detailsViewModel: DetailsViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)
        //val bottomNavigationItemView=view.findViewById<BottomNavigationView>(R.id.navigation)
       // bottomNavigationItemView.visibility=View.GONE
      // val view2 = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
        activity?.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)!!.visibility=View.GONE

        // new code
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        currentUserId = sharedPreferences.getInt("userId", -1)

        getDetailsViewModelReady()

        val recipy = args.Meal
        image_detail = view.findViewById(R.id.imageDetails)
        category = view.findViewById(R.id.tv_category)
        Ares = view.findViewById(R.id.tv_area)
        recipy_name = view.findViewById(R.id.tv_recipy_name)
        instruction = view.findViewById(R.id.tv_instruction)
        favCheckBox = view.findViewById(R.id.checkbox_fav)
        video = view.findViewById(R.id.youtube_player_view)
        category.text = recipy.strCategory
        Ares.text = recipy.strArea
        recipy_name.text = recipy.strMeal
        instruction.text = recipy.strInstructions
        val video_id = getVideoId(recipy.strYoutube)

        // new code
       checkIsFav(recipy)


        video.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(video_id.toString(), 0F)
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


        // new code
        favCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                detailsViewModel.insertRecipe(recipy)
                detailsViewModel.insertIntoFavList(UserFavList(currentUserId, recipy.idMeal))

            }else{
                showCustomDialog(recipy)
            }
        }


        return view
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
    override fun onDetach() {
        activity?.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)!!.visibility=View.VISIBLE

        super.onDetach()
    }

    fun checkIsFav(recipe: Meal){
        detailsViewModel.checkFavRecipe(currentUserId, recipe.idMeal)
        detailsViewModel.isFav.observe(viewLifecycleOwner){
            favCheckBox.isChecked = it
        }
    }

    private fun showCustomDialog(recipe: Meal){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnDelete: Button = dialog.findViewById(R.id.btn_delete_dialog)
        val btnCancel: Button = dialog.findViewById(R.id.btn_cancel_dialog)

        btnDelete.setOnClickListener {
            // delete recipe from db
            detailsViewModel.deleteFromFavList(UserFavList(currentUserId, recipe.idMeal))
            Toast.makeText(context, "The recipe is deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            // need to change the heart color
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
            favCheckBox.isChecked = true
        }
        dialog.show()
    }

    private fun getDetailsViewModelReady(){
        val factory = DetailsVMFactory(
            DetailsRepoImpl(ApiClient, LocalDatabaseImpl(requireContext().applicationContext))
        )  // send instance of Imp for repo
        detailsViewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
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
}

