package com.miigubymia.inventory.dataBase

import android.app.Application

class InventoryApplication: Application() {

    val database by lazy {InventoryDatabase.getDatabase(this)}
    val repository by lazy { InventoryRepository(database.getInventoryDao()) }

}