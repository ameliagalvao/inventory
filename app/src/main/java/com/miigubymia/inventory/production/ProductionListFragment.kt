package com.miigubymia.inventory.production

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miigubymia.inventory.R
import com.miigubymia.inventory.clients.ClientsAdapter
import com.miigubymia.inventory.clients.SingleClientActivity
import com.miigubymia.inventory.dataBase.*

class ProductionListFragment : Fragment() {

    lateinit var productionAdapter: ProductionAdapter
    lateinit var productionViewModel: ProductionViewModel

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

        val recyclerView: RecyclerView = view.findViewById(R.id.rvProduction)
        recyclerView.layoutManager = LinearLayoutManager(context)
        productionAdapter = ProductionAdapter()
        recyclerView.adapter = productionAdapter

        return view
    }

}