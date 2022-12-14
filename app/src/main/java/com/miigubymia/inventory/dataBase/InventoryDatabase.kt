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
                     ], version = 5)
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

        val migration_2_3: Migration = object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `new_artisan_table`(" +
                        "`artisanName` TEXT NOT NULL," +
                        "`artisanPix` TEXT NOT NULL," +
                        "`artisanPhone` TEXT NOT NULL," +
                        "`artisanSkills` TEXT NOT NULL," +
                        "`artisanProduction` TEXT NOT NULL," +
                        "PRIMARY KEY(`artisanName`))")
                database.execSQL("DROP TABLE artisan_table")
                database.execSQL("ALTER TABLE new_artisan_table RENAME TO artisan_table")
                //PRODUCTS
                database.execSQL("CREATE TABLE `new_products_table`(" +
                        "`productName` TEXT NOT NULL," +
                        "`productdesigner` TEXT NOT NULL," +
                        "`productdescription` TEXT NOT NULL," +
                        "`productHandWorkCost` INTEGER NOT NULL," +
                        "`productAvailableInventory` INTEGER NOT NULL," +
                        "PRIMARY KEY(`productName`))")
                database.execSQL("DROP TABLE products_table")
                database.execSQL("ALTER TABLE new_products_table RENAME TO products_table")
                //PRODUCTION
                database.execSQL("CREATE TABLE `new_production_table`(" +
                        "`artisanName` TEXT NOT NULL," +
                        "`productName` TEXT NOT NULL," +
                        "`productionQuantity` INTEGER NOT NULL," +
                        "`date` TEXT NOT NULL," +
                        "`id` INTEGER NOT NULL," +
                        "PRIMARY KEY(`id`))")
                database.execSQL("DROP TABLE production_table")
                database.execSQL("ALTER TABLE new_production_table RENAME TO production_table")
            }
        }

        val migration_3_4: Migration = object : Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `new_artisan_table`(" +
                        "`artisanName` TEXT NOT NULL," +
                        "`artisanPix` TEXT NOT NULL," +
                        "`artisanPhone` TEXT NOT NULL," +
                        "`artisanSkills` TEXT NOT NULL," +
                        "`artisanProduction` TEXT NOT NULL," +
                        "`artisanID` INTEGER NOT NULL," +
                        "PRIMARY KEY(`artisanID`))")
                database.execSQL("DROP TABLE artisan_table")
                database.execSQL("ALTER TABLE new_artisan_table RENAME TO artisan_table")
                //PRODUCTS
                database.execSQL("CREATE TABLE `new_products_table`(" +
                        "`productName` TEXT NOT NULL," +
                        "`productdesigner` TEXT NOT NULL," +
                        "`productdescription` TEXT NOT NULL," +
                        "`productHandWorkCost` INTEGER NOT NULL," +
                        "`productAvailableInventory` INTEGER NOT NULL," +
                        "`productID` INTEGER NOT NULL," +
                        "PRIMARY KEY(`productID`))")
                database.execSQL("DROP TABLE products_table")
                database.execSQL("ALTER TABLE new_products_table RENAME TO products_table")
            }
        }

        val migration_4_5: Migration = object : Migration(4,5){
            override fun migrate(database: SupportSQLiteDatabase) {
                //PRODUCTION
                database.execSQL("CREATE TABLE `new_production_table`(" +
                        "`artisanName` TEXT NOT NULL," +
                        "`productName` TEXT NOT NULL," +
                        "`productionQuantity` INTEGER NOT NULL," +
                        "`date` TEXT NOT NULL," +
                        "`productionID` INTEGER NOT NULL," +
                        "`artisanID` INTEGER NOT NULL," +
                        "PRIMARY KEY(`productionID`)," +
                        "FOREIGN KEY(`artisanID`) REFERENCES `Artisan`(`artisanID`) ON UPDATE NO ACTION)")
                database.execSQL("DROP TABLE production_table")
                database.execSQL("ALTER TABLE new_production_table RENAME TO production_table")
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope):InventoryDatabase{
            // só permite a criação de uma instância por vez.
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                InventoryDatabase::class.java,"inventory_database")
                    .allowMainThreadQueries()
                    .addMigrations(migration_1_2, migration_2_3, migration_3_4)
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
                    inventoryDAO.insertClient(Clients("Miigu", "Rua dos bobos, sem número, Recife, PE", "Fulana", "819902939239", "29831083210", "fulana@gmail.com"))
                    inventoryDAO.insertClient(Clients("Hotel Luz", "Rua dos tolos, 123, Porto de Galinhas, PE", "Joana", "81999999999", "657575775", "joana@gmail.com"))
                    inventoryDAO.insertProduct(Products("Bailarina", "Ana", "Bailarina em amigurumi, articulada com roupa em tecido."))
                    inventoryDAO.insertProduct(Products("Bolsa Bel", "Chris", "Bolsa em crochê com ponto vazado. Apresenta duas cores e vem com forro com bolsos internos."))
                    inventoryDAO.insertSupply(Supplies("Linha Amigurumi", "Linha", 13.90, "100% algodão"))
                    inventoryDAO.insertSupply(Supplies("Linha Apollo", "Barbante", 18.90, "Algodão e fibra Eco"))
                    inventoryDAO.insertProduction(Production("Cicrana da Silva", "Bolsa Bel", 5, "27/10/2022", 1))
                    inventoryDAO.insertProduction(Production("Cicrana da Silva", "Bolsa Bel", 10, "15/10/2022", 1))
                }
            }

        }
    }
}