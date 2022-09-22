package com.miigubymia.inventory.artisans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.dataBase.ArtisanViewModelFactory
import com.miigubymia.inventory.dataBase.InventoryApplication
import com.miigubymia.inventory.databinding.ActivityRvartisansBinding

class RVArtisansActivity : AppCompatActivity() {

    lateinit var binding: ActivityRvartisansBinding
    lateinit var artisanViewModel: ArtisanViewModel
    lateinit var artisanAdapter:ArtisanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvartisansBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModelFactory = ArtisanViewModelFactory((application as InventoryApplication).repository)
        val recyclerView:RecyclerView = binding.artisiansListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        artisanAdapter = ArtisanAdapter()
        recyclerView.adapter = artisanAdapter


        artisanViewModel = ViewModelProvider(this, viewModelFactory).get(ArtisanViewModel::class.java)

        artisanViewModel.allArtisans.observe(this,Observer{artisans ->
            artisanAdapter.setArtisan(artisans)
        })

        //SearchView
        val searchView = binding.searchBarArtisan
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    searchArtisansInDatabase(query)
                }
                return true
            }

            //vai ser chamada sempre que digitarmos um caracter no view
            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null){
                    searchArtisansInDatabase(query)
                }
                return true
            }
        })

    }

        private fun searchArtisansInDatabase(query:String){
            val searchQuery = "%$query%"
            artisanViewModel.searchArtisan(searchQuery).observe(this) { list ->
                list.let {
                    artisanAdapter.setArtisan(it)
                }
            }
        }

}