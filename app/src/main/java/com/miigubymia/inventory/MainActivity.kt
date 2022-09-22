package com.miigubymia.inventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miigubymia.inventory.artisans.RVArtisansActivity
import com.miigubymia.inventory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.btnGoToArtisans.setOnClickListener {
            val intent = Intent(this@MainActivity, RVArtisansActivity::class.java)
            startActivity(intent)
        }

    }
}