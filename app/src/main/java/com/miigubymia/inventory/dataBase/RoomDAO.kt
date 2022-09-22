package com.miigubymia.inventory.dataBase

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDAO {

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

}