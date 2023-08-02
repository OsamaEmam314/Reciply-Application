package com.reciply

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.reciply.R
import com.reciply.network.ServiceViewModel

class AuthActivity : AppCompatActivity() {
     lateinit var viewModel: ServiceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel= ViewModelProvider(this).get(ServiceViewModel::class.java)
        viewModel.getMealById("a")
    }
}