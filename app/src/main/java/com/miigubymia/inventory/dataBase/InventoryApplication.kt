package com.miigubymia.inventory.dataBase

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class InventoryApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy {InventoryDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { InventoryRepository(database.getInventoryDao()) }

}