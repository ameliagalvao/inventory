package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "products_table")
data class Products (
    val productName:String,
    val productdesigner:String,
    val productdescription:String
        ): Serializable {
    var productHandWorkCost:Int = 0
    var productAvailableInventory:Int = 0
    @PrimaryKey(autoGenerate = true)
    var productID = 0
    // supplies
    // models
}