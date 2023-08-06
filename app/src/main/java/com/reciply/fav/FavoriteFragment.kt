package com.reciply.fav

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reciply.R
import com.google.android.material.textfield.TextInputLayout
import com.reciply.data.models.Meal
import com.reciply.search.view.SearchRecyclerAdapter

class FavoriteFragment : Fragment() {

    private val TAG  = "FavoriteFragment"
//    lateinit var etFavSearch: TextInputLayout
//    lateinit var recyclerFav: RecyclerView

//    lateinit var nav_controller: NavController
//    lateinit var adapterFav: SearchRecyclerAdapter
//    var listOfFav: List<Meal> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        etFavSearch = view.findViewById(R.id.et_search_fav_frg)
//        recyclerFav = view.findViewById(R.id.recycler_fav_frg)

//        nav_controller = findNavController()
//
//        adapterFav = SearchRecyclerAdapter(requireContext(), nav_controller)
//        recyclerFav.adapter = adapterFav

//        recyclerFav.layoutManager = GridLayoutManager(requireContext(), 2)


    }

    private fun showCustomDialog(){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnDelete: Button = dialog.findViewById(R.id.btn_delete_dialog)
        val btnCancel: Button = dialog.findViewById(R.id.btn_cancel_dialog)

        btnDelete.setOnClickListener {
            // delete recipe from db
        }
        btnCancel.setOnClickListener {
            // hide the dialog
            dialog.dismiss()
        }
        dialog.show()
    }

}