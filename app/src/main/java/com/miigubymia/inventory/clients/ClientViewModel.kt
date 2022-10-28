package com.miigubymia.inventory.clients

import androidx.lifecycle.*
import com.miigubymia.inventory.dataBase.Clients
import com.miigubymia.inventory.dataBase.InventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientViewModel (private val repository: InventoryRepository): ViewModel() {

    val allClients : LiveData<List<Clients>> = repository.allClients.asLiveData()

    fun insertClient(client: Clients) = viewModelScope.launch(Dispatchers.IO){
        repository.insertClient(client)
    }

    fun deleteClient(client: Clients) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteClient(client)
    }

    fun updateClient(client: Clients) = viewModelScope.launch(Dispatchers.IO){
        repository.updateClient(client)
    }

    fun searchClient(searchQuery:String):LiveData<List<Clients>>{
        return repository.searchClient(searchQuery).asLiveData()
    }

}

class ClientViewModelFactory(private var repository: InventoryRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClientViewModel::class.java)){
            return ClientViewModel(repository) as T
        }else{
            throw IllegalArgumentException("unknown view model")
        }
    }
}