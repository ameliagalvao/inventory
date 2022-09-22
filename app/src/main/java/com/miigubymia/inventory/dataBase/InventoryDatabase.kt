package com.miigubymia.inventory.dataBase

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Artisan::class], version = 1)
abstract class InventoryDatabase: RoomDatabase() {

    abstract fun getInventoryDao():InventoryDAO

    companion object{
        // o companion permite que a gente acesse essa variável de qualquer lugar do app
        // o volatile deixa a instância criada a partir dessa thread visível para todas as outras threads.
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope):InventoryDatabase{
            // só permite a criação de uma instância por vez.
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                InventoryDatabase::class.java,"inventory_database")
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