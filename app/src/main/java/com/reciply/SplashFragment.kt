package com.reciply

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.reciply.R

private lateinit var rootView: View

class SplashFragment : Fragment() {

    private companion object {
        const val SPLASH_DURATION = 1500L
        const val FADE_IN_DELAY = 300L
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_splash, container, false)
        val imageView = rootView.findViewById<ImageView>(R.id.img_animatedhat_splash)
        val rotateAnimation: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_animation)
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                rootView.postDelayed({
                    startFadeInAnimation(rootView)
                }, FADE_IN_DELAY)
            }
        })
        imageView.startAnimation(rotateAnimation)

        return rootView
    }

    private fun startFadeInAnimation(rootView: View) {
        val reciplyTextView = rootView.findViewById<TextView>(R.id.txt_ReciplyAnimated_splash)
        val sloganTextView = rootView.findViewById<TextView>(R.id.txt_sloganAnimated_splash)
        val reciplyAnimation = ObjectAnimator.ofFloat(reciplyTextView, "alpha", 0f, 1f)
        val sloganAnimation = ObjectAnimator.ofFloat(sloganTextView, "alpha", 0f, 1f)
        reciplyAnimation.duration = 1500
        sloganAnimation.duration = 1500
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(reciplyAnimation, sloganAnimation)
        animatorSet.start()
        rootView.postDelayed({
            val isUserLoggedIn = IsUserLoggedInSharedPreferences()
            if (isUserLoggedIn) {
                navigateToRecipeActivity()
            } else {
                navigateToLoginFragment()
            }
        }, SPLASH_DURATION)
    }

    private fun navigateToRecipeActivity() {
        val intent = Intent(requireContext(), RecipeActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun navigateToLoginFragment() {
        val action = SplashFragmentDirections.actionSplashFragment2ToLoginFragment2()
        findNavController().navigate(action)
    }

    private fun IsUserLoggedInSharedPreferences(): Boolean {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isUserLoggedIn", false)
    }
}
