package com.example.myapp.ui.apilist.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.ActivityApiListBinding
import com.example.myapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)
        callNextActivity()
    }

    private fun callNextActivity() {
        handler = Handler()
        handler?.postDelayed({
            startActivity(Intent(this@SplashActivity,ApiListActivity::class.java))
            finish()
        }, 1500)
    }
}