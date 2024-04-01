package com.example.myapp.ui.apidatalist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.ActivityApiListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityApiListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiListBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)
    }
}