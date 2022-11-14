package com.miigubymia.inventory.dataBase

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductionViewModel (private val repository: InventoryRepository): ViewModel() {

    val allProduction : LiveData<List<Production>> = repository.allProduction.asLiveData()

    fun insertProduction(production: Production) = viewModelScope.launch(Dispatchers.IO){
        repository.insertProduction(production)
    }

    fun deleteProduction(production: Production) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteProduction(production)
    }

    fun updateProduction(production: Production) = viewModelScope.launch(Dispatchers.IO){
        repository.updateProduction(production)
    }

    fun getProductionPerArtisan(artisanID:Int) = viewModelScope.launch(Dispatchers.IO){
        repository.getArtisanWithProductions(artisanID)
    }

    fun getProductionPerProduct(productID:Int) = viewModelScope.launch(Dispatchers.IO){
        repository.getArtisanWithProductions(productID)
    }

}

class ProductionViewModelFactory(private var repository: InventoryRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductionViewModel::class.java)){
            return ProductionViewModel(repository) as T
        }else{
            throw IllegalArgumentException("unknown view model")
        }
    }
}