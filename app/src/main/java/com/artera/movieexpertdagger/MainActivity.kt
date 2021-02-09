package com.artera.movieexpertdagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.artera.movieexpertdagger.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.main_nav_fragment)
        navController.setGraph(R.navigation.main_navigation)
        binding.mainNavBottom.setupWithNavController(navController)

        Timber.d("Start App")
    }
}