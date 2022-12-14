package com.miigubymia.inventory.dataBase

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class InventoryRepository(private val inventoryDAO: InventoryDAO) {

    ////////////////////////// Artisans
    val allArtisans : Flow<List<Artisan>> = inventoryDAO.getAllArtisans()

    @WorkerThread
    suspend fun insertArtisan(artisan: Artisan){
        inventoryDAO.insertArtisan(artisan)
    }

    @WorkerThread
    suspend fun deleteArtisan(artisan: Artisan){
        inventoryDAO.deleteArtisan(artisan)
    }

    @WorkerThread
    suspend fun updateArtisan(artisan: Artisan){
        inventoryDAO.updateArtisan(artisan)
    }

    @WorkerThread
    suspend fun deleteAllArtisans(){
        inventoryDAO.deleteAllArtisans()
    }

    @WorkerThread
    fun searchArtisan(searchQuery:String):Flow<List<Artisan>>{
        return inventoryDAO.searchArtisan(searchQuery)
    }

    ////////////////////////// Clients
    val allClients : Flow<List<Clients>> = inventoryDAO.getAllClients()

    @WorkerThread
    suspend fun insertClient(client: Clients){
        inventoryDAO.insertClient(client)
    }

    @WorkerThread
    suspend fun deleteClient(client: Clients){
        inventoryDAO.deleteClient(client)
    }

    @WorkerThread
    suspend fun updateClient(client: Clients){
        inventoryDAO.updateClient(client)
    }

    @WorkerThread
    suspend fun deleteAllClients(){
        inventoryDAO.deleteAllClients()
    }

    @WorkerThread
    fun searchClient(searchQuery:String):Flow<List<Clients>>{
        return inventoryDAO.searchClient(searchQuery)
    }

    ////////////////////////// Products
    val allProducts : Flow<List<Products>> = inventoryDAO.getAllProducts()

    @WorkerThread
    suspend fun insertProduct(product: Products){
        inventoryDAO.insertProduct(product)
    }

    @WorkerThread
    suspend fun deleteProduct(product: Products){
        inventoryDAO.deleteProduct(product)
    }

    @WorkerThread
    suspend fun updateProduct(product: Products){
        inventoryDAO.updateProduct(product)
    }

    @WorkerThread
    suspend fun deleteAllProducts(){
        inventoryDAO.deleteAllProducts()
    }

    @WorkerThread
    fun searchProduct(searchQuery:String):Flow<List<Products>>{
        return inventoryDAO.searchProduct(searchQuery)
    }

    ////////////////////////// Supplies
    val allSupplies : Flow<List<Supplies>> = inventoryDAO.getAllSupplies()

    @WorkerThread
    suspend fun insertSupply(supply: Supplies){
        inventoryDAO.insertSupply(supply)
    }

    @WorkerThread
    suspend fun deleteSupply(supply: Supplies){
        inventoryDAO.deleteSupply(supply)
    }

    @WorkerThread
    suspend fun updateSupply(supply: Supplies){
        inventoryDAO.updateSupply(supply)
    }

    @WorkerThread
    suspend fun deleteAllSupplies(){
        inventoryDAO.deleteAllSupplies()
    }

    @WorkerThread
    fun searchSupplies(searchQuery:String):Flow<List<Supplies>>{
        return inventoryDAO.searchSupply(searchQuery)
    }

    ////////////////////////// Production
    val allProduction : Flow<List<Production>> = inventoryDAO.getAllProduction()

    @WorkerThread
    suspend fun insertProduction(production: Production){
        inventoryDAO.insertProduction(production)
    }

    @WorkerThread
    suspend fun deleteProduction(production: Production){
        inventoryDAO.deleteProduction(production)
    }

    @WorkerThread
    suspend fun updateProduction(production: Production){
        inventoryDAO.updateProduction(production)
    }

    ////////////////////////// Relations
    @WorkerThread
    suspend fun getArtisanWithProductions(artisanName:String): Flow<List<ArtisanWithProductions>>{
        return inventoryDAO.getArtisanWithProductions(artisanName)
    }

    @WorkerThread
    suspend fun getProductWithProductions(productName:String): Flow<List<ProductWithProductions>>{
        return inventoryDAO.getProductWithProductions(productName)
    }
}