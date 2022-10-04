package com.miigubymia.inventory.artisans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.miigubymia.inventory.R

class EditSingleArtisanFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_single_artisan, container, false)

        val cancelbtn = view.findViewById<Button>(R.id.btnCancelEdit)

        cancelbtn.setOnClickListener {
            dialog!!.dismiss()
        }

        return view
    }
}