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
            createList()
            val content = productionListToSave.joinToString(separator = "\n")
            write(requireContext(), requireActivity(), content)
            Toast.makeText(context, "Salvo.", Toast.LENGTH_LONG).show()
        }

        return view
    }

    fun write(context: Context, activity: Activity, content:String) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Permissão Necessária")
            builder.setMessage("Para que o aplicativo possa salvar o registro, você precisa liberar a permissão de acesso aos arquivos.")
            builder.setPositiveButton("Conceder permissão") { dialog, which ->
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                Toast.makeText(context, "Permissão NÃO concedida", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }else{
            val externalDir = activity.applicationContext.getExternalFilesDir(null)
            val file = File(externalDir, "registro_producao.txt")
            try {
                if (!file.exists()) {
                    file.createNewFile()
                }
                val writer = FileWriter(file, true)
                writer.use { it.write(content) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createList(){
        productionListToSave.clear()
        productionListToSave.add("ID da Produção,Data de Entrega,Nome do Artesao,Nome do Produto,Quantidade")
        val productionList = productionAdapter.production
        var loop = 0
        for (item in productionList){
            var id = productionList[loop].id.toString()
            var date = productionList[loop].date
            var artisanName = productionList[loop].artisanName
            var productName = productionList[loop].productName
            var quantity = productionList[loop].productionQuantity.toString()
            var line = "$id,$date,$artisanName,$productName,$quantity"
            productionListToSave.add(line)
            loop += 1
        }
    }

}