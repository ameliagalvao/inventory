package com.miigubymia.inventory.dataBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtisanViewModel(private val repository: InventoryRepository): ViewModel() {

    val allArtisans : LiveData<List<Artisan>> = repository.allArtisans.asLiveData()

    fun insertArtisan(artisan: Artisan) = viewModelScope.launch(Dispatchers.IO){
        repository.insertArtisan(artisan)
    }

    fun deleteArtisan(artisan: Artisan) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteArtisan(artisan)
    }

    fun updateArtisan(artisan: Artisan) = viewModelScope.launch(Dispatchers.IO){
        repository.updateArtisan(artisan)
    }

    fun deleteAllArtisans() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAllArtisans()
    }

}