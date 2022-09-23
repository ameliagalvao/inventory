package com.miigubymia.inventory.artisans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miigubymia.inventory.R
import com.miigubymia.inventory.databinding.FragmentSinglePageArtisanBinding

class SinglePageArtisanFragment : Fragment() {

    private var _binding:FragmentSinglePageArtisanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSinglePageArtisanBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_single_page_artisan, container, false)


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}