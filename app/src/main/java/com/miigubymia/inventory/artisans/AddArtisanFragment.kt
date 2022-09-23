package com.miigubymia.inventory.artisans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.miigubymia.inventory.R

class AddArtisanFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_artisan, container, false)
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        //botão para voltar para o ListView.
        val btnCancel = view.findViewById<Button>(R.id.btnCancelAdd)
        btnCancel.setOnClickListener {
            dialog!!.dismiss()
        }

        //botão para salvar dados
        val btnRegisterArtisan = view.findViewById<Button>(R.id.btnregisterNewArtisan)
        val radioGroupPix = view.findViewById<RadioGroup>(R.id.radioGroupPix)
        btnRegisterArtisan.setOnClickListener {
            if (radioGroupPix.checkedRadioButtonId != -1 ){
                Toast.makeText(context, "validado",Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}