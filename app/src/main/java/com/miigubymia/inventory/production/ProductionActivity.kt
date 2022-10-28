package com.miigubymia.inventory.production

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miigubymia.inventory.databinding.ActivityProductionBinding

class ProductionActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}