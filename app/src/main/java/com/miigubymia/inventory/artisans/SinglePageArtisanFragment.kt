package com.miigubymia.inventory.artisans

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.Artisan
import com.miigubymia.inventory.dataBase.ArtisanViewModel
import com.miigubymia.inventory.dataBase.ArtisanViewModelFactory
import com.miigubymia.inventory.dataBase.InventoryApplication


class SinglePageArtisanFragment : Fragment() {

    lateinit var artisanAdapter:ArtisanAdapter
    lateinit var artisanViewModel: ArtisanViewModel

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
        val view = inflater.inflate(R.layout.fragment_single_page_artisan, container, false)
        var currentArtisan = activity?.intent?.getSerializableExtra("clickedArtisan") as Artisan

        val nameTextView = view.findViewById<TextView>(R.id.tvSingleArtisanName)
        val pixTextView = view.findViewById<TextView>(R.id.tvSinglePixToFill)
        val skillsTextView = view.findViewById<TextView>(R.id.tvSingleSkillsToFill)
        val deletebtn = view.findViewById<Button>(R.id.btnSingleDelete)
        val contactbtn = view.findViewById<Button>(R.id.btnPhoneSingle)
        val editbtn = view.findViewById<Button>(R.id.btnSingleEdit)

        nameTextView.text = currentArtisan.artisanName
        pixTextView.text = currentArtisan.artisanPix
        skillsTextView.text = currentArtisan.artisanSkills

        contactbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${currentArtisan.artisanPhone}")
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context,getString(R.string.appNotFound), Toast.LENGTH_SHORT).show()
            }
        }

        deletebtn.setOnClickListener {
            val dialogBuilder = context?.let { it1 -> AlertDialog.Builder(it1) }
            dialogBuilder?.setTitle(getString(R.string.deleteArtisan))
            dialogBuilder?.setMessage(getString(R.string.areYouSure))
            dialogBuilder?.setPositiveButton(getString(R.string.btnDelete)) { dialog, whichButton ->
                requireActivity().onBackPressed()
                artisanViewModel.deleteArtisan(currentArtisan)
                Toast.makeText(context, getString(R.string.deleted), Toast.LENGTH_SHORT).show()
            }
            dialogBuilder?.setNegativeButton(getString(R.string.cancel)) { dialog, whichButton ->
                dialog.cancel()
            }
            dialogBuilder?.create()?.show()
        }

        editbtn.setOnClickListener {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val dialogFragment = EditSingleArtisanFragment()
            dialogFragment.isCancelable = false
            //Para o dialog não usamos o transaction
            dialogFragment.show(fragmentManager, "EditSingleArtisan")
        }

        return view
    }

}