package com.miigubymia.inventory.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.miigubymia.inventory.R
import com.miigubymia.inventory.clients.AddClientFragment
import com.miigubymia.inventory.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}