package com.miigubymia.inventory.artisans

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.miigubymia.inventory.R

class AddArtisanFragment() : DialogFragment() {

    lateinit var radioGroupPix: RadioGroup
    lateinit var artisanName: EditText
    lateinit var artisanPhone: EditText
    lateinit var artisanPix: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_artisan, container, false)
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        //fields
        artisanName = view.findViewById(R.id.etAddArtisanName)
        artisanPhone = view.findViewById(R.id.etAddArtisanPhone)
        artisanPix = view.findViewById(R.id.etAddArtisanPix)

        //botão para voltar para o ListView.
        val btnCancel = view.findViewById<Button>(R.id.btnCancelAdd)
        btnCancel.setOnClickListener {
            dialog!!.dismiss()
        }

        //botão para salvar dados
        val btnRegisterArtisan = view.findViewById<Button>(R.id.btnregisterNewArtisan)
        radioGroupPix = view.findViewById<RadioGroup>(R.id.radioGroupPix)
        btnRegisterArtisan.setOnClickListener {
            if (validation()) {
                Toast.makeText(context, "validado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Preencha todos os itens", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun validation(): Boolean {
        var result = false
        result = when {
            radioGroupPix.checkedRadioButtonId == -1 -> false
            TextUtils.isEmpty(artisanName.text.toString()) -> false
            TextUtils.isEmpty(artisanPhone.text.toString()) -> false
            TextUtils.isEmpty(artisanPix.text.toString()) -> false
            else -> true
        }
        return result
    }
}


/*

// checkboxes
    lateinit var cbAmigurumi: CheckBox
    lateinit var cbCroche: CheckBox
    lateinit var cbMacrame: CheckBox
    lateinit var cbSergio: CheckBox
    lateinit var cbCostura: CheckBox
    lateinit var cbTecelagem: CheckBox

        cbAmigurumi = view.findViewById(R.id.cbAmigurumi)
        cbCroche = view.findViewById(R.id.cbCroche)
        cbMacrame = view.findViewById(R.id.cbMacrame)
        cbSergio = view.findViewById(R.id.cbSergio)
        cbCostura = view.findViewById(R.id.cbCostura)
        cbTecelagem = view.findViewById(R.id.cbTecelagem)

        cbAmigurumi.isChecked -> cbAmigurumi.text = "Amigurumi"
        cbCroche.isChecked ->  cbCroche.text = "Croche"
        cbMacrame.isChecked -> cbMacrame.text = "Macrame"
        cbTecelagem.isChecked -> cbTecelagem.text = "Tecelagem"
        cbCostura.isChecked -> cbCostura.text = "Costura"
        cbSergio.isChecked -> cbSergio.text = "Sergio Matos"

 */