package com.miigubymia.inventory.production

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.*
import com.miigubymia.inventory.products.ProductsViewModel
import com.miigubymia.inventory.products.ProductsViewModelFactory
import java.util.*

class AddNewProductionFragment : Fragment() {

    lateinit var artisanViewModel: ArtisanViewModel
    lateinit var productViewModel: ProductsViewModel
    lateinit var productionViewModel: ProductionViewModel
    lateinit var productionAdapter: ProductionAdapter
    lateinit var autoCompleteArtisan:AutoCompleteTextView
    lateinit var autoCompleteProducts:AutoCompleteTextView
    lateinit var etQuantity:EditText
    var currentProductId = 0
    var currentArtisanId = 0
    var currentProduct = ""
    var currentArtisan = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productionAdapter = ProductionAdapter()
        val productionViewModelFactory = ProductionViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        productionViewModel = ViewModelProvider(this, productionViewModelFactory).get(ProductionViewModel::class.java)
        productionViewModel.allProduction.observe(viewLifecycleOwner,Observer{production ->
            productionAdapter.setCraftProduction(production)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_new_production, container, false)
        val btnViewProduction = view.findViewById<Button>(R.id.btnViewProduction)

        //AutoCompleteViewArtisan
        autoCompleteArtisan = view.findViewById<AutoCompleteTextView>(R.id.tvChooseArtisan)
        val viewModelFactory = ArtisanViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        artisanViewModel = ViewModelProvider(this, viewModelFactory).get(ArtisanViewModel::class.java)
        artisanViewModel.allArtisans.observe(viewLifecycleOwner, Observer{ artisans ->
            // Update the cached copy of the students in the adapter.
            artisans?.let {
                val arr = mutableMapOf<String, Int>()
                val arr2 = mutableListOf<String>()
                for (value in it) {
                    arr[value.artisanName] = value.artisanID
                    arr2.add(value.artisanName)
                }
                val adapter: ArrayAdapter<String> =
                    ArrayAdapter(requireContext(), android.R.layout.select_dialog_item, arr2)
                autoCompleteArtisan.setAdapter(adapter)
                autoCompleteArtisan.setOnItemClickListener { _, _, position, _ ->
                    // Pegar o que foi clicado:
                    val value = adapter.getItem(position) ?: ""
                    currentArtisanId = arr.get(value)!!
                    currentArtisan = arr2[0]
                }
            }
        })

        //AutoCompleteProducts
        autoCompleteProducts = view.findViewById<AutoCompleteTextView>(R.id.tvChooseProduct)
        var productID = ""
        val productsViewModelFactory = ProductsViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        productViewModel = ViewModelProvider(this, productsViewModelFactory).get(ProductsViewModel::class.java)
        productViewModel.allProducts.observe(viewLifecycleOwner, Observer{ products ->
            // Update the cached copy of the students in the adapter.
            products?.let {
                val arr = mutableMapOf<String, Int>()
                val arr2 = mutableListOf<String>()
                for (value in it) {
                    arr[value.productName] = value.productID
                    arr2.add(value.productName)
                }
                val adapter: ArrayAdapter<String> =
                    ArrayAdapter(requireContext(), android.R.layout.select_dialog_item, arr2)
                autoCompleteProducts.setAdapter(adapter)
                autoCompleteProducts.setOnItemClickListener { _, _, position, _ ->
                    // Pegar o que foi clicado:
                    val value = adapter.getItem(position) ?: ""
                    currentProductId = arr.get(value)!!
                    currentProduct = arr2[0]
                }
            }
        })

        //Quantity
        etQuantity = view.findViewById<EditText>(R.id.etProductionQuantity)

        // Buttons
        val btnRegister = view.findViewById<Button>(R.id.btnProductionSave)
        val btnCancel = view.findViewById<Button>(R.id.btnProductionCancel)

        btnCancel.setOnClickListener {
            autoCompleteArtisan.setText("")
            autoCompleteProducts.setText("")
            etQuantity.setText("")
        }

        btnRegister.setOnClickListener {
            var teste = mutableListOf<List<Artisan>>()
            if (validation()) {
                val artisan = autoCompleteArtisan.text.toString()
                val productionQuantity = etQuantity.text.toString().toInt()
                // Data
                val simpleFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val currentDate = simpleFormat.format(Date())
                val production = Production(currentArtisan, currentProduct, productionQuantity, currentDate, currentArtisanId)
                productionViewModel.insertProduction(production)
                autoCompleteArtisan.setText("")
                autoCompleteProducts.setText("")
                etQuantity.setText("")
            } else {
                Toast.makeText(context, getString(R.string.fillAll), Toast.LENGTH_SHORT).show()
            }
        }

        btnViewProduction.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_nav_new_production_to_productionListFragment)
        }

        return view
    }
    private fun validation(): Boolean {
        var result = false
        result = when {
            TextUtils.isEmpty(autoCompleteArtisan.text.toString()) -> false
            TextUtils.isEmpty(autoCompleteProducts.text.toString()) -> false
            TextUtils.isEmpty(etQuantity.text.toString()) -> false
            else -> true
        }
        return result
    }

}