package com.miigubymia.inventory.clients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miigubymia.inventory.R
import com.miigubymia.inventory.artisans.ArtisanAdapter
import com.miigubymia.inventory.dataBase.Clients

class ClientAdapter: RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    var clients: List<Clients> = ArrayList()

    //Clicável
    private lateinit var clientListener: onClientClickListener
    interface onClientClickListener{
        fun onClientClick(position:Int)
    }
    fun setOnClientClickListener(listener: onClientClickListener){
        clientListener = listener
    }

    class ClientViewHolder(itemView: View, listener: ClientAdapter.onClientClickListener):RecyclerView.ViewHolder(itemView){
        val tvClientName: TextView = itemView.findViewById(R.id.tvClientName)
        init {
            itemView.setOnClickListener {
                listener.onClientClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.client_card_view,parent,false)
        return ClientViewHolder(view, clientListener)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        var currentClient:Clients = clients[position]
        holder.tvClientName.text = currentClient.businessName
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    fun setClientsList(refreshClientsList: List<Clients>){
        this.clients = refreshClientsList
        notifyDataSetChanged()
    }
}