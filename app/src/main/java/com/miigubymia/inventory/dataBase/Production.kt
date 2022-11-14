package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "production_table")
data class Production(
    val artisanName:String,
    val productName:String,
    val productionQuantity:Int,
    val date:String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}