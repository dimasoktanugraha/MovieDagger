package com.artera.movieexpertdagger.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.artera.movieexpertdagger.MainActivity
import com.artera.movieexpertdagger.R
import com.artera.movieexpertdagger.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            moveToMain()
        },2000)
    }

    private fun moveToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
        finish()
    }
}