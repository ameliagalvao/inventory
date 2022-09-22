package com.miigubymia.inventory.artisans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.dataBase.ArtisanViewModelFactory
import com.miigubymia.inventory.dataBase.InventoryApplication
import com.miigubymia.inventory.databinding.ActivityRvartisansBinding

class RVArtisansActivity : AppCompatActivity() {

    lateinit var binding: ActivityRvartisansBinding
    lateinit var artisanViewModel: ArtisanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvartisansBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModelFactory = ArtisanViewModelFactory((application as InventoryApplication).repository)

        artisanViewModel = ViewModelProvider(this, viewModelFactory).get(ArtisanViewModel::class.java)
        artisanViewModel.allArtisans.observe(this,Observer{

        })

    }
}