package com.miigubymia.inventory.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDAO {

    // Artisans
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

    // Clients
    @Insert
    suspend fun insertClient(client:Clients)

    @Delete
    suspend fun deleteClient(client:Clients)

    @Update
    suspend fun updateClient(client:Clients)

    @Query("SELECT * FROM clients_table ORDER BY businessName ASC")
    fun getAllClients():Flow<List<Clients>>

    @Query("DELETE FROM clients_table")
    suspend fun deleteAllClients()

    @Query("SELECT * FROM clients_table WHERE businessName LIKE :searchQuery")
    fun searchClient(searchQuery:String): Flow<List<Clients>>

    @Query("SELECT * FROM clients_table WHERE id=:id")
    fun getClientById(id:Int):LiveData<Clients>

    // Products
    @Insert
    suspend fun insertProduct(product:Products)

    @Delete
    suspend fun deleteProduct(product:Products)

    @Update
    suspend fun updateProduct(product:Products)

    @Query("SELECT * FROM products_table ORDER BY productName ASC")
    fun getAllProducts():Flow<List<Products>>

    @Query("DELETE FROM products_table")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM products_table WHERE productName LIKE :searchQuery")
    fun searchProduct(searchQuery:String): Flow<List<Products>>

    // Supplies
    @Insert
    suspend fun insertSupply(supply:Supplies)

    @Delete
    suspend fun deleteSupply(supply:Supplies)

    @Update
    suspend fun updateSupply(supply:Supplies)

    @Query("SELECT * FROM supplies_table ORDER BY name ASC")
    fun getAllSupplies():Flow<List<Supplies>>

    @Query("DELETE FROM supplies_table")
    suspend fun deleteAllSupplies()

    @Query("SELECT * FROM supplies_table WHERE name LIKE :searchQuery")
    fun searchSupply(searchQuery:String): Flow<List<Supplies>>

    @Query("SELECT * FROM supplies_table WHERE id=:id")
    fun getSupplyById(id:Int):LiveData<Supplies>

    // Production
    @Insert
    suspend fun insertProduction(production: Production)

    @Delete
    suspend fun deleteProduction(production: Production)

    @Update
    suspend fun updateProduction(production: Production)

    @Query("SELECT * FROM production_table ORDER BY id ASC")
    fun getAllProduction():Flow<List<Production>>

    // Relations
    @Transaction
    @Query("SELECT * FROM artisan_table WHERE artisanName = :artisanName")
    fun getArtisanWithProductions(artisanName:String): Flow<List<ArtisanWithProductions>>

    @Transaction
    @Query("SELECT * FROM products_table WHERE productName = :productName")
    fun getProductWithProductions(productName:String): Flow<List<ProductWithProductions>>

}