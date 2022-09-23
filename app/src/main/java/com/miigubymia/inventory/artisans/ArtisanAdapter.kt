package com.miigubymia.inventory.artisans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.Artisan

class ArtisanAdapter: RecyclerView.Adapter<ArtisanAdapter.ArtisanViewHolder>() {

    var artisans: List<Artisan> = ArrayList()

    //Clicável
    private lateinit var artisanListener: onArtisanClickListener
    interface onArtisanClickListener{
        fun onArtisanClick(position:Int)
    }
    fun setOnArtisanClickListener(listener: onArtisanClickListener){
        artisanListener = listener
    }

    class ArtisanViewHolder (itemView: View, listener: onArtisanClickListener):RecyclerView.ViewHolder(itemView){
        val textViewArtisanName:TextView = itemView.findViewById(R.id.tvArtisanName)
        val textViewArtisanSkills:TextView = itemView.findViewById(R.id.tvArtisanSkills)
        val imageViewArtisanPhoto:ImageView = itemView.findViewById(R.id.ivArtisanPhoto)
        val cardView:CardView = itemView.findViewById(R.id.artisanCardView)
        //clicavel
        init {
            itemView.setOnClickListener {
                listener.onArtisanClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtisanViewHolder {
        val view:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.artisan_card,parent,false)
        return ArtisanViewHolder(view, artisanListener)
    }

    override fun onBindViewHolder(holder: ArtisanViewHolder, position: Int) {
        var currentArtisan : Artisan = artisans[position]

        holder.textViewArtisanName.text = currentArtisan.artisanName
        holder.textViewArtisanSkills.text = "Habilidades: " + currentArtisan.artisanSkills
        holder.imageViewArtisanPhoto.setImageResource(R.drawable.p1)
    }

    override fun getItemCount(): Int {
        return artisans.size
    }

    fun setArtisan(refreshArtisan: List<Artisan>){
        this.artisans = refreshArtisan
        notifyDataSetChanged()
    }
}