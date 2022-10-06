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

    lateinit var radioGroupEdit: RadioGroup
    lateinit var cbEditAmigurumi: CheckBox
    lateinit var cbEditCostura: CheckBox
    lateinit var cbEditCroche: CheckBox
    lateinit var cbEditMacrame: CheckBox
    lateinit var cbEditSergio: CheckBox
    lateinit var cbEditTecelagem: CheckBox

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

        //checkBoxes
        cbEditAmigurumi = view.findViewById(R.id.cbEditAmigurumi)
        cbEditCroche = view.findViewById(R.id.cbEditCroche)
        cbEditMacrame = view.findViewById(R.id.cbEditMacrame)
        cbEditSergio = view.findViewById(R.id.cbEditSergio)
        cbEditCostura = view.findViewById(R.id.cbEditSew)
        cbEditTecelagem = view.findViewById(R.id.cbEditTecelagem)

        val checkedSkills = currentArtisan.artisanSkills
        checkSkills(checkedSkills)

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

    private fun checkSkills(skill: String) {
        if (skill.contains("Amigurumi")) {
            cbEditAmigurumi.isChecked = true
        }
        if (skill.contains("Macramê")) {
            cbEditMacrame.isChecked = true
        }
        if (skill.contains("Sérgio Matos")) {
            cbEditSergio.isChecked = true
        }
        if (skill.contains("Tecelagem")) {
            cbEditTecelagem.isChecked = true
        }
        if (skill.contains("Costura")) {
            cbEditCostura.isChecked = true
        }
        if (skill.contains("Crochê")) {
            cbEditCroche.isChecked = true
        }
    }

}