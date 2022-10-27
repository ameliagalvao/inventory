package com.miigubymia.inventory.dataBase

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [
    Artisan::class,
    Clients::class,
    Production::class,
    Products::class,
    Supplies::class
                     ], version = 2)
abstract class InventoryDatabase: RoomDatabase() {

    abstract fun getInventoryDao():InventoryDAO

    companion object{
        // o companion permite que a gente acesse essa variável de qualquer lugar do app
        // o volatile deixa a instância criada a partir dessa thread visível para todas as outras threads.
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        val migration_1_2: Migration =object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `clients_table`(`businessName`TEXT NOT NULL," +
                        "`address`TEXT NOT NULL, `contactPerson`TEXT NOT NULL,`contactPhone`TEXT NOT NULL," +
                        "`cpfOrCnpj`TEXT NOT NULL, `email`TEXT NOT NULL, `id`INTEGER NOT NULL, PRIMARY KEY(`id`))")
                database.execSQL("CREATE TABLE `production_table`(`artisanId`INTEGER NOT NULL," +
                        "`productId`INTEGER NOT NULL, `productionQuantity`INTEGER NOT NULL, `date`TEXT NOT NULL," +
                        "`id`INTEGER NOT NULL, PRIMARY KEY(`id`))")
                database.execSQL("CREATE TABLE `products_table`(`name`TEXT NOT NULL," +
                        "`designer`TEXT NOT NULL, `description`TEXT NOT NULL,`handWorkCost`INTEGER NOT NULL, " +
                        "`availableInventory`INTEGER NOT NULL, `id`INTEGER NOT NULL, PRIMARY KEY(`id`))")
                database.execSQL("CREATE TABLE `supplies_table`(`name`TEXT NOT NULL," +
                        "`kind`TEXT NOT NULL, `cost`DOUBLE NOT NULL,`material`TEXT NOT NULL, " +
                        "`quantityAvailable`INTEGER NOT NULL, `id`INTEGER NOT NULL, PRIMARY KEY(`id`))")
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope):InventoryDatabase{
            // só permite a criação de uma instância por vez.
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                InventoryDatabase::class.java,"inventory_database")
                    .allowMainThreadQueries()
                    .addMigrations(migration_1_2)
                    .addCallback(InventoryDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class InventoryDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    val inventoryDAO = database.getInventoryDao()
                    inventoryDAO.insertArtisan(Artisan("Cicrana da Silva", "Telefone 81988868584", "81988868584", "Amigurumi, Crochê"))
                    inventoryDAO.insertArtisan(Artisan("Fulano dos Santos", "CPF 89578435972", "81988875652", "Macramê"))
                    inventoryDAO.insertArtisan(Artisan("Beltrana das Graças", "Email beltrana@provedor.com", "81994858264", "Costura, CrochÊ"))
                }
            }

        }
    }
}