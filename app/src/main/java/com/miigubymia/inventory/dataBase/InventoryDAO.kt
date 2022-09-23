package com.miigubymia.inventory.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDAO {

    @Insert
    suspend fun insertArtisan(artisan: Artisan)

    @Delete
    suspend fun deleteArtisan(artisan: Artisan)

    @Update
    suspend fun updateArtisan(artisan: Artisan)

    @Query("SELECT * FROM artisan_table ORDER BY artisanName ASC")
    fun getAllArtisans():Flow<List<Artisan>>

    @Query("DELETE FROM artisan_table")
    suspend fun deleteAllArtisans()

    @Query("SELECT * FROM artisan_table WHERE artisanName LIKE :searchQuery")
    fun searchArtisan(searchQuery:String): Flow<List<Artisan>>

    @Query("SELECT * FROM artisan_table WHERE id=:id")
    fun getArtisanById(id:Int):LiveData<Artisan>

}