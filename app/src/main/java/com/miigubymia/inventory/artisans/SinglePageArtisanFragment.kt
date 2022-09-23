package com.miigubymia.inventory.artisans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.dataBase.ArtisanViewModelFactory
import com.miigubymia.inventory.dataBase.InventoryApplication
import androidx.lifecycle.Observer

class SinglePageArtisanFragment : Fragment() {

    lateinit var artisanAdapter:ArtisanAdapter
    lateinit var artisanViewModel: ArtisanViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        artisanAdapter = ArtisanAdapter()
        val viewModelFactory = ArtisanViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        artisanViewModel = ViewModelProvider(this, viewModelFactory).get(ArtisanViewModel::class.java)
        artisanViewModel.allArtisans.observe(viewLifecycleOwner,Observer{artisans ->
            artisanAdapter.setArtisan(artisans)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_single_page_artisan, container, false)
        var clickedArtisanId = activity?.intent?.getIntExtra("clickedArtisanId", 0)

        return view
    }

}