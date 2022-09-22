package com.miigubymia.inventory.artisans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.databinding.ActivityArtisansBinding

class ArtisansActivity : AppCompatActivity() {

    lateinit var binding: ActivityArtisansBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtisansBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}