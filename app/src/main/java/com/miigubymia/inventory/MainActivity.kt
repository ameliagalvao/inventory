package com.miigubymia.inventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miigubymia.inventory.artisans.ArtisansActivity
import com.miigubymia.inventory.clients.ClientsActivity
import com.miigubymia.inventory.databinding.ActivityMainBinding
import com.miigubymia.inventory.products.ProductsActivity

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.btnGoToArtisans.setOnClickListener {
            val intent = Intent(this@MainActivity, ArtisansActivity::class.java)
            startActivity(intent)
        }

        mainBinding.btnGoToClients.setOnClickListener {
            val intent = Intent(this@MainActivity, ClientsActivity::class.java)
            startActivity(intent)
        }

        mainBinding.btnGoToProducts.setOnClickListener {
            val intent = Intent(this@MainActivity, ProductsActivity::class.java)
            startActivity(intent)
        }

    }
}