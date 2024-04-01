package com.example.myapp.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapp.databinding.ActivitySplashBinding
import com.example.myapp.ui.apidatalist.ApiListActivity
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
            startActivity(Intent(this@SplashActivity, ApiListActivity::class.java))
            finish()
        }, 1500)
    }
}