package com.miigubymia.inventory.dataBase

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class InventoryRepository(private val inventoryDAO: InventoryDAO) {

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

}