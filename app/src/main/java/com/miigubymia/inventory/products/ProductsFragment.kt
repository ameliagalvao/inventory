package com.miigubymia.inventory.products

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miigubymia.inventory.R
import com.miigubymia.inventory.artisans.AddArtisanFragment
import com.miigubymia.inventory.dataBase.InventoryApplication

class ProductsFragment : Fragment() {

    lateinit var productsAdapter: ProductsAdapter
    lateinit var productViewModel: ProductsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ProductsViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        productViewModel = ViewModelProvider(this, viewModelFactory).get(ProductsViewModel::class.java)
        productViewModel.allProducts.observe(viewLifecycleOwner, Observer{ products ->
            productsAdapter.setProduct(products)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvProductsList)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        productsAdapter = ProductsAdapter()
        recyclerView.adapter = productsAdapter

        //SearchView
        val searchView = view.findViewById<SearchView>(R.id.searchViewProducts)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    searchProductInDatabase(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null){
                    searchProductInDatabase(query)
                }
                return true
            }
        })

        productsAdapter.setOnProductClickListener(object : ProductsAdapter.onProductClickListener {
            override fun onProductClick(position: Int) {
                val clickedProduct = productsAdapter.products[position]
                val intent = Intent(context, SingleProductActivity::class.java)
                // Toast.makeText(context, "$clickedId", Toast.LENGTH_SHORT).show()
                intent.putExtra("clickedProduct", clickedProduct)
                startActivity(intent)
            }

        })

        val fabAddProduct = view.findViewById<FloatingActionButton>(R.id.fabAddProduct)

        fabAddProduct.setOnClickListener{
            Toast.makeText(context, "Ainda não implementado", Toast.LENGTH_SHORT).show()
        }

        return view
    }
    private fun searchProductInDatabase(query:String){
        val searchQuery = "%$query%"
        productViewModel.searchProduct(searchQuery).observe(this) { list ->
            list.let {
                productsAdapter.setProduct(it)
            }
        }
    }
}