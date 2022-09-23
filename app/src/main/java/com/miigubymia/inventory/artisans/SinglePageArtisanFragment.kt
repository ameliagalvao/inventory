package com.miigubymia.inventory.artisans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.dataBase.ArtisanViewModelFactory
import com.miigubymia.inventory.dataBase.InventoryApplication
import androidx.lifecycle.Observer
import com.miigubymia.inventory.dataBase.Artisan

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
        var currentArtisan = activity?.intent?.getSerializableExtra("clickedArtisan") as Artisan

        val nameTextView = view.findViewById<TextView>(R.id.tvSingleArtisanName)
        val pixTextView = view.findViewById<TextView>(R.id.tvSinglePixToFill)
        val phoneTextView = view.findViewById<TextView>(R.id.tvSinglePhoneToFill)
        val skillsTextView = view.findViewById<TextView>(R.id.tvSingleSkillsToFill)
        val deletebtn = view.findViewById<Button>(R.id.btnSingleDelete)

        nameTextView.text = currentArtisan.artisanName
        pixTextView.text = currentArtisan.artisanPix
        phoneTextView.text = currentArtisan.artisanPhone
        skillsTextView.text = currentArtisan.artisanSkills

        deletebtn.setOnClickListener {
            requireActivity().onBackPressed()
            Toast.makeText(context, "Artesão Excluído", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}