package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class Products (
    val name:String,
    val designer:String,
    val description:String
        ) {
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
    val handWorkCost:Int = 0
    val availableInventory = ""
    // supplies
    // models
}