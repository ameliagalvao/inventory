package com.miigubymia.inventory.production

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.Production

class ProductionAdapter: RecyclerView.Adapter<ProductionAdapter.ProductionViewHolder>() {

    var production: List<Production> = ArrayList()

    class ProductionViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        val tvProductionDate: TextView = itemView.findViewById(R.id.tvProductionDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.production_card,parent,false)
        return ProductionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductionViewHolder, position: Int) {
        var currentProduction:Production = production[position]
        holder.tvProductionDate.text = currentProduction.date
    }

    override fun getItemCount(): Int {
        return production.size
    }

    fun setCraftProduction(refreshProduction: List<Production>){
        this.production = refreshProduction
        notifyDataSetChanged()
    }
}