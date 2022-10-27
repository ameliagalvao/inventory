package com.miigubymia.inventory.clients

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miigubymia.inventory.R
import com.miigubymia.inventory.dataBase.*

class AddClientFragment : DialogFragment() {

    lateinit var clientBusinessName: EditText
    lateinit var clientDocument: EditText
    lateinit var clientAddress: EditText
    lateinit var clientContactPerson: EditText
    lateinit var clientPhone: EditText
    lateinit var clientEmail: EditText
    lateinit var clientViewModel: ClientViewModel
    lateinit var clientAdapter: ClientsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        clientAdapter = ClientsAdapter()
        val viewModelFactory = ClientViewModelFactory((activity?.applicationContext as InventoryApplication).repository)
        clientViewModel = ViewModelProvider(this, viewModelFactory).get(ClientViewModel::class.java)
        clientViewModel.allClients.observe(viewLifecycleOwner, Observer{ clients ->
            clientAdapter.setClientsList(clients)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_client, container, false)

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        //fields
        clientBusinessName = view.findViewById(R.id.etAddbusinessName)
        clientDocument = view.findViewById(R.id.etAddClientCnpjOrCPF)
        clientAddress = view.findViewById(R.id.etAddClientAddress)
        clientContactPerson = view.findViewById(R.id.etAddContactPersonName)
        clientPhone = view.findViewById(R.id.etAddAClientPhone)
        clientEmail = view.findViewById(R.id.etAddClientEmail)

        //botão para voltar para o ListView.
        val btnCancel = view.findViewById<Button>(R.id.btnCancelAddClient)
        btnCancel.setOnClickListener {
            dialog!!.dismiss()
        }

        //botão para salvar dados
        val btnRegisterClient = view.findViewById<Button>(R.id.btnRegisterNewClient)
        btnRegisterClient.setOnClickListener {
            if (validation()) {
                val client = Clients(clientBusinessName.text.toString(), clientAddress.text.toString(), clientContactPerson.text.toString(), clientPhone.text.toString(), clientDocument.text.toString(), clientEmail.text.toString())
                clientViewModel.insertClient(client)
                dialog!!.dismiss()
                Toast.makeText(context, getString(R.string.addsucess), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, getString(R.string.fillAll), Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun validation(): Boolean {
        var result = false
        result = when {
            TextUtils.isEmpty(clientBusinessName.text.toString()) -> false
            TextUtils.isEmpty(clientDocument.text.toString()) -> false
            TextUtils.isEmpty(clientAddress.text.toString()) -> false
            TextUtils.isEmpty(clientContactPerson.text.toString()) -> false
            TextUtils.isEmpty(clientPhone.text.toString()) -> false
            TextUtils.isEmpty(clientEmail.text.toString()) -> false
            else -> true
        }
        return result
    }

}