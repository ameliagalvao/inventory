package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "products_table")
data class Products (
    val name:String,
    val designer:String,
    val description:String
        ): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    var handWorkCost:Int = 0
    var availableInventory:Int = 0
    // supplies
    // models
}