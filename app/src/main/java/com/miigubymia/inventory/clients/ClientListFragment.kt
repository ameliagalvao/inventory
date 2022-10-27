package com.miigubymia.inventory.clients

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.*

class ClientListFragment : Fragment() {

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

        //SearchView
        val searchView = view.findViewById<SearchView>(R.id.searchViewClients)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    searchClientsInDatabase(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null){
                    searchClientsInDatabase(query)
                }
                return true
            }
        })

        clientAdapter.setOnClientClickListener(object : ClientsAdapter.onClientClickListener {
            override fun onClientClick(position: Int) {
                val clickedClient = clientAdapter.clients[position]
                val clickedClientID = clientAdapter.clients[position].id
                val intent = Intent(context, SingleClientActivity::class.java)
                // Toast.makeText(context, "$clickedId", Toast.LENGTH_SHORT).show()
                intent.putExtra("clickedClient", clickedClient)
                startActivity(intent)
            }

        })


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