package com.miigubymia.inventory.artisans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.Artisan

class EditSingleArtisanFragment : DialogFragment() {

    lateinit var radioGroupEdit:RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_single_artisan, container, false)

        var currentArtisan = activity?.intent?.getSerializableExtra("clickedArtisan") as Artisan

        val cancelbtn = view.findViewById<Button>(R.id.btnCancelEdit)
        val confirmbtn = view.findViewById<Button>(R.id.btnEditConfirm)

        val editTextName = view.findViewById<EditText>(R.id.etNameEdit)
        editTextName.setHint(currentArtisan.artisanName)

        val editTextPhone = view.findViewById<EditText>(R.id.etEditPhone)
        editTextPhone.setHint(currentArtisan.artisanPhone)

        val editTextPix = view.findViewById<EditText>(R.id.etEditArtisanPix)
        val pixWithoutRadioBtn = currentArtisan.artisanPix.substringAfterLast(' ')
        editTextPix.setHint(pixWithoutRadioBtn)

        radioGroupEdit = view.findViewById(R.id.radioGroupEditPix)
        checkPixOption(currentArtisan.artisanPix)

        cancelbtn.setOnClickListener {
            dialog!!.dismiss()
        }

        confirmbtn.setOnClickListener {
            Toast.makeText(context, "Alterado", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun checkPixOption(pix: String) {
        when {
            pix.contains("Telefone") -> radioGroupEdit.check(R.id.rbtnEditPhone)
            pix.contains("E-mail") || pix.contains("Email") -> radioGroupEdit.check(R.id.rbtnEditEmail)
            pix.contains("CPF") -> radioGroupEdit.check(R.id.rbtnEditCPF)
            else -> false
        }
    }

}