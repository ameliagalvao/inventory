package com.miigubymia.inventory.artisans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.Artisan

class EditSingleArtisanFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_single_artisan, container, false)

        val cancelbtn = view.findViewById<Button>(R.id.btnCancelEdit)
        val confirmbtn = view.findViewById<Button>(R.id.btnEditConfirm)
        var currentArtisan = activity?.intent?.getSerializableExtra("clickedArtisan") as Artisan
        val editTextName = view.findViewById<EditText>(R.id.etNameEdit)
        editTextName.setHint(currentArtisan.artisanName)

        cancelbtn.setOnClickListener {
            dialog!!.dismiss()
        }

        confirmbtn.setOnClickListener {
            Toast.makeText(context, "Alterado", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}