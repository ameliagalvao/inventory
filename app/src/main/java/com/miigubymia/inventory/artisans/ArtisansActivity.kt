package com.miigubymia.inventory.artisans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.databinding.ActivityRvartisansBinding

class ArtisansActivity : AppCompatActivity() {

    lateinit var binding: ActivityRvartisansBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvartisansBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}