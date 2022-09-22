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

    class ArtisanViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewArtisanName:TextView = itemView.findViewById(R.id.tvArtisanName)
        val textViewArtisanSkills:TextView = itemView.findViewById(R.id.tvArtisanSkills)
        val imageViewArtisanPhoto:ImageView = itemView.findViewById(R.id.ivArtisanPhoto)
        val cardView:CardView = itemView.findViewById(R.id.artisanCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtisanViewHolder {
        val view:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.artisan_card,parent,false)
        return ArtisanViewHolder(view)
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