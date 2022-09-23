package com.miigubymia.inventory.artisans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miigubymia.inventory.R
import com.miigubymia.inventory.databinding.ActivitySingleArtisanBinding

class SingleArtisanActivity : AppCompatActivity() {

    lateinit var binding: ActivitySingleArtisanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleArtisanBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}