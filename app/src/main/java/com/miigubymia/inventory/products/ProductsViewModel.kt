package com.miigubymia.inventory.products

import androidx.lifecycle.*
import com.miigubymia.inventory.dataBase.InventoryRepository
import com.miigubymia.inventory.dataBase.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel (private val repository: InventoryRepository): ViewModel() {

    val allProducts : LiveData<List<Products>> = repository.allProducts.asLiveData()

    fun insertProduct(product: Products) = viewModelScope.launch(Dispatchers.IO){
        repository.insertProduct(product)
    }

    fun deleteProduct(product: Products) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteProduct(product)
    }

    fun updateProduct(product: Products) = viewModelScope.launch(Dispatchers.IO){
        repository.updateProduct(product)
    }

    fun searchProduct(searchQuery:String): LiveData<List<Products>> {
        return repository.searchProduct(searchQuery).asLiveData()
    }

}

class ProductsViewModelFactory(private var repository: InventoryRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)){
            return ProductsViewModel(repository) as T
        }else{
            throw IllegalArgumentException("unknown view model")
        }
    }
}