package com.miigubymia.inventory.clients

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.miigubymia.inventory.databinding.ActivityClientsBinding

class ClientsActivity : AppCompatActivity() {

    lateinit var binding: ActivityClientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.fabAddClient.setOnClickListener{
            val fragmentManager: FragmentManager = this.supportFragmentManager
            val dialogFragment = AddClientFragment()
            dialogFragment.isCancelable = false
            dialogFragment.show(fragmentManager, "AddClient")
        }
    }
}