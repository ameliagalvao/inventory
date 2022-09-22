package com.miigubymia.inventory.dataBase

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room

@Database(entities = [Artisan::class], version = 1)
abstract class InventoryDatabase: RoomDatabase() {

    abstract fun getInventoryDao():InventoryDAO

    companion object{
        // o companion permite que a gente acesse essa variável de qualquer lugar do app
        // o volatile deixa a instância criada a partir dessa thread visível para todas as outras threads.
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        fun getDatabase(context: Context):InventoryDatabase{
            // só permite a criação de uma instância por vez.
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                InventoryDatabase::class.java,"inventory_database").build()
                INSTANCE = instance
                instance
            }
        }
    }

}