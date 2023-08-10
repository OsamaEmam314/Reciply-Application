package com.reciply.aboutUs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.reciply.R
import com.reciply.data.models.Person

class AboutFragment : Fragment() {

    lateinit var recyclerAboutUs: RecyclerView
    lateinit var adapterAbout: AboutUsRecyclerAdapter
    var persons = mutableListOf<Person>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)!!.visibility=View.GONE
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        persons.add(Person("Osama Khaled", "OsamaEmam314", R.drawable.person_icon_1))
        persons.add(Person("Mona Adel", "MonaAdel2", R.drawable.person_icon_2))
        persons.add(Person("Martina Ashraf", "8Martina8", R.drawable.person_icon_3))
        persons.add(Person("Shimaa Hussein", "ShimaaHussein12", R.drawable.person_icon_4))

        recyclerAboutUs = view.findViewById(R.id.recycler_about_us)
        recyclerAboutUs.adapter = AboutUsRecyclerAdapter(requireContext(), persons)
        recyclerAboutUs.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

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

}