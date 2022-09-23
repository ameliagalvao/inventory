package com.miigubymia.inventory.artisans

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.Artisan
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.dataBase.ArtisanViewModelFactory
import com.miigubymia.inventory.dataBase.InventoryApplication
import androidx.lifecycle.Observer

class AddArtisanFragment() : DialogFragment() {

    lateinit var radioGroupPix: RadioGroup
    lateinit var artisanName: EditText
    lateinit var artisanPhone: EditText
    lateinit var artisanPix: EditText
    lateinit var cbAmigurumi: CheckBox
    lateinit var cbCostura: CheckBox
    lateinit var cbCroche: CheckBox
    lateinit var cbMacrame: CheckBox
    lateinit var cbSergio: CheckBox
    lateinit var cbTecelagem: CheckBox
    var result = ""
    lateinit var artisanViewModel: ArtisanViewModel
    lateinit var artisanAdapter:ArtisanAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        artisanAdapter = ArtisanAdapter()
        val viewModelFactory = ArtisanViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        artisanViewModel = ViewModelProvider(this, viewModelFactory).get(ArtisanViewModel::class.java)
        artisanViewModel.allArtisans.observe(viewLifecycleOwner,Observer{artisans ->
            artisanAdapter.setArtisan(artisans)
        })
    }

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

        //checkBoxes
        cbAmigurumi = view.findViewById(R.id.cbAmigurumi)
        cbCroche = view.findViewById(R.id.cbCroche)
        cbMacrame = view.findViewById(R.id.cbMacrame)
        cbSergio = view.findViewById(R.id.cbSergio)
        cbCostura = view.findViewById(R.id.cbSew)
        cbTecelagem = view.findViewById(R.id.cbTecelagem)

        //botão para salvar dados
        val btnRegisterArtisan = view.findViewById<Button>(R.id.btnregisterNewArtisan)
        radioGroupPix = view.findViewById<RadioGroup>(R.id.radioGroupPix)
        btnRegisterArtisan.setOnClickListener {
            if (validation()) {
                checkboxes()
                val pixrbtn = getRadioBtn()
                val artisanPix:String = pixrbtn + artisanPix.text.toString()
                val artisan = Artisan(artisanName.text.toString(),artisanPix,artisanPhone.text.toString(),result)
                artisanViewModel.insertArtisan(artisan)
                dialog!!.dismiss()
                Toast.makeText(context, "Artesão adicionado com sucesso.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Por favor, preencha todos os itens", Toast.LENGTH_SHORT).show()
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

    private fun getRadioBtn():String{
        var selectedBtn = ""
        selectedBtn = when {
            radioGroupPix.checkedRadioButtonId == 0 -> "Telefone: "
            radioGroupPix.checkedRadioButtonId == 1 -> "CPF: "
            radioGroupPix.checkedRadioButtonId == 2 -> "Email: "
            else -> ""
        }
        return selectedBtn
    }

    fun checkboxes(): String {
            if (cbCroche.isChecked) result += "Croche "
            if (cbMacrame.isChecked) result += "Macrame "
            if (cbTecelagem.isChecked) result += "Tecelagem "
            if (cbCostura.isChecked) result += "Costura "
            if (cbSergio.isChecked) result += "Sergio Matos "
            if (cbAmigurumi.isChecked) result += "Amigurumi"
        return result
    }
}
