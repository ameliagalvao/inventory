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

class EditSingleArtisanFragment : DialogFragment() {

    lateinit var radioGroupEdit: RadioGroup
    lateinit var cbEditAmigurumi: CheckBox
    lateinit var cbEditCostura: CheckBox
    lateinit var cbEditCroche: CheckBox
    lateinit var cbEditMacrame: CheckBox
    lateinit var cbEditSergio: CheckBox
    lateinit var cbEditTecelagem: CheckBox
    var result = ""
    lateinit var editTextPhone:EditText
    lateinit var editTextName:EditText
    lateinit var editTextPix:EditText
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_single_artisan, container, false)

        var currentArtisan = activity?.intent?.getSerializableExtra("clickedArtisan") as Artisan
        val artisanID = currentArtisan.id

        val cancelbtn = view.findViewById<Button>(R.id.btnCancelEdit)
        val confirmbtn = view.findViewById<Button>(R.id.btnEditConfirm)

        editTextName = view.findViewById<EditText>(R.id.etNameEdit)
        editTextName.setText(currentArtisan.artisanName)

        editTextPhone = view.findViewById<EditText>(R.id.etEditPhone)
        editTextPhone.setText(currentArtisan.artisanPhone)

        editTextPix = view.findViewById<EditText>(R.id.etEditArtisanPix)
        val pixWithoutRadioBtn = currentArtisan.artisanPix.substringAfterLast(' ')
        editTextPix.setText(pixWithoutRadioBtn)

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
            if (validation()) {
                checkboxes()
                val pixrbtn = getRadioBtn()
                val artisanPix:String = pixrbtn + editTextPix.text.toString()
                val artisan = Artisan(editTextName.text.toString(),artisanPix,editTextPhone.text.toString(),result)
                artisan.id = artisanID
                artisanViewModel.updateArtisan(artisan)
                dialog!!.dismiss()
                Toast.makeText(context, "Atualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, getString(R.string.fillAll), Toast.LENGTH_SHORT).show()
            }
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

    private fun validation(): Boolean {
        var result = false
        result = when {
            radioGroupEdit.checkedRadioButtonId == -1 -> false
            TextUtils.isEmpty(editTextName.text.toString()) -> false
            TextUtils.isEmpty(editTextPhone.text.toString()) -> false
            TextUtils.isEmpty(editTextPix.text.toString()) -> false
            else -> true
        }
        return result
    }

    private fun getRadioBtn():String{
        var selectedBtn = ""
        when(radioGroupEdit.checkedRadioButtonId){
            R.id.rbtnEditPhone -> selectedBtn = "${getString(R.string.phone)}: "
            R.id.rbtnEditCPF -> selectedBtn = "${getString(R.string.cpf)}: "
            R.id.rbtnEditEmail -> selectedBtn = "${getString(R.string.email)}: "
            else -> ""
        }
        return selectedBtn
    }

    fun checkboxes(): String {
        if (cbEditCroche.isChecked) result += "${getString(R.string.crochet)} "
        if (cbEditMacrame.isChecked) result += "${getString(R.string.macrame)} "
        if (cbEditTecelagem.isChecked) result += "${getString(R.string.tecelagem)} "
        if (cbEditCostura.isChecked) result += "${getString(R.string.sewing)} "
        if (cbEditSergio.isChecked) result += "${getString(R.string.sergioMatos)} "
        if (cbEditAmigurumi.isChecked) result += "${getString(R.string.amigurumi)} "
        return result
    }

}