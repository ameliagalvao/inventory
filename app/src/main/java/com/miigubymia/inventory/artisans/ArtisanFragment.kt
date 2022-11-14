package com.miigubymia.inventory.artisans

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.dataBase.ArtisanViewModelFactory
import com.miigubymia.inventory.dataBase.InventoryApplication

class ArtisanFragment : Fragment() {

    lateinit var artisanViewModel: ArtisanViewModel
    lateinit var artisanAdapter:ArtisanAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ArtisanViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        artisanViewModel = ViewModelProvider(this, viewModelFactory).get(ArtisanViewModel::class.java)
        artisanViewModel.allArtisans.observe(viewLifecycleOwner, Observer{ artisans ->
            artisanAdapter.setArtisan(artisans)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artisans_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvArtisansListFragment)
        recyclerView.layoutManager = LinearLayoutManager(context)
        artisanAdapter = ArtisanAdapter()
        recyclerView.adapter = artisanAdapter

        //SearchView
        val searchView = view.findViewById<SearchView>(R.id.searchBarArtisan)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

        artisanAdapter.setOnArtisanClickListener(object : ArtisanAdapter.onArtisanClickListener {
            override fun onArtisanClick(position: Int) {
                val clickedArtisan = artisanAdapter.artisans[position]
                val clickedArtisanID = artisanAdapter.artisans[position].id
                val intent = Intent(context, SingleArtisanActivity::class.java)
                // Toast.makeText(context, "$clickedId", Toast.LENGTH_SHORT).show()
                intent.putExtra("clickedArtisan", clickedArtisan)
                startActivity(intent)
            }

        })

        val fabAddArtisan = view.findViewById<FloatingActionButton>(R.id.fabAddArtisan)

        fabAddArtisan.setOnClickListener{
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val dialogFragment = AddArtisanFragment()
            dialogFragment.isCancelable = false
            //Para o dialog não usamos o transaction
            dialogFragment.show(fragmentManager, "ShowArtisan")
        }

        return view
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
