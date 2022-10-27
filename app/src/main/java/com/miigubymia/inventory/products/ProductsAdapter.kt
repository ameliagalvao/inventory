package com.miigubymia.inventory.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.Products

class ProductsAdapter: RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    var products: List<Products> = ArrayList()

    //Clicável
    private lateinit var productListener: onProductClickListener
    interface onProductClickListener{
        fun onProductClick(position:Int)
    }
    fun setOnProductClickListener(listener: onProductClickListener){
        productListener = listener
    }

    class ProductsViewHolder(itemView: View, listener: ProductsAdapter.onProductClickListener):
        RecyclerView.ViewHolder(itemView){
        val tvProductName: TextView = itemView.findViewById(R.id.tvListProductName)
        init {
            itemView.setOnClickListener {
                listener.onProductClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card,parent,false)
        return ProductsViewHolder(view, productListener)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        var currentProduct: Products = products[position]
        holder.tvProductName.text = currentProduct.name
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setProduct(refreshProduct: List<Products>){
        this.products = refreshProduct
        notifyDataSetChanged()
    }
}