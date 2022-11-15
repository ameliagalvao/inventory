package com.miigubymia.inventory.production

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.*
import java.io.File
import java.io.FileWriter

class ProductionListFragment : Fragment() {

    lateinit var productionAdapter: ProductionAdapter
    lateinit var productionViewModel: ProductionViewModel
    val productionListToSave = mutableListOf("vazia")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ProductionViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        productionViewModel = ViewModelProvider(this, viewModelFactory).get(ProductionViewModel::class.java)
        productionViewModel.allProduction.observe(viewLifecycleOwner, Observer{ production ->
            productionAdapter.setCraftProduction(production)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_production_list, container, false)
        val btnRegister = view.findViewById<Button>(R.id.btnSaveFileProduction)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvProduction)
        recyclerView.layoutManager = LinearLayoutManager(context)
        productionAdapter = ProductionAdapter()
        recyclerView.adapter = productionAdapter

        btnRegister.setOnClickListener {
            createList(productionListToSave, productionAdapter)
            val content = productionListToSave.joinToString(separator = "\n")
            writeToExternalStorage(requireContext(), requireActivity(), content)
            writeTextInInternalStorage(requireContext(), requireActivity(), content)
        }

        return view
    }

}