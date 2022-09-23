package com.miigubymia.inventory.dataBase

import androidx.lifecycle.*
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

    fun searchArtisan(searchQuery:String):LiveData<List<Artisan>>{
        return repository.searchArtisan(searchQuery).asLiveData()
    }

    fun getArtisanById(id:Int): LiveData<Artisan>{
        return repository.getArtisanById(id)
    }

}

class ArtisanViewModelFactory(private var repository: InventoryRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtisanViewModel::class.java)){
            return ArtisanViewModel(repository) as T
        }else{
            throw IllegalArgumentException("unknown view model")
        }
    }
}