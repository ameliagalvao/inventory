package com.miigubymia.inventory.clients

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.InventoryApplication

class ClientsFragment : Fragment() {

    lateinit var clientAdapter: ClientsAdapter
    lateinit var clientViewModel: ClientViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ClientViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        clientViewModel = ViewModelProvider(this, viewModelFactory).get(ClientViewModel::class.java)
        clientViewModel.allClients.observe(viewLifecycleOwner, Observer{ clients ->
            clientAdapter.setClientsList(clients)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_client_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvClientList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        clientAdapter = ClientsAdapter()
        recyclerView.adapter = clientAdapter

        clientAdapter.setOnClientClickListener(object : ClientsAdapter.onClientClickListener {
            override fun onClientClick(position: Int) {
                val clickedClient = clientAdapter.clients[position]
                // val clickedClientID = clientAdapter.clients[position].id
                // val intent = Intent(context, SingleClientActivity::class.java)
                Toast.makeText(context, "$clickedClient", Toast.LENGTH_SHORT).show()
                //  intent.putExtra("clickedClient", clickedClient)
                // startActivity(intent)
            }

        })

        val fabAddClient = view.findViewById<FloatingActionButton>(R.id.fabAddClient)

        fabAddClient.setOnClickListener{
            Toast.makeText(context, "Ainda não implementado", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun searchClientsInDatabase(query:String){
        val searchQuery = "%$query%"
        clientViewModel.searchClient(searchQuery).observe(this) { list ->
            list.let {
                clientAdapter.setClientsList(it)
            }
        }
    }

}