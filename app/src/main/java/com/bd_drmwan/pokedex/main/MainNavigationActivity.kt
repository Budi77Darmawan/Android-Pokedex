package com.bd_drmwan.pokedex.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bd_drmwan.pokedex.databinding.ActivityMainNavigationBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onViewCreated()
    }

    private fun onViewCreated() {
    }
}