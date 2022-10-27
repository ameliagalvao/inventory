package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supplies_table")
data class Supplies(
    val name:String,
    val kind:String,
    val color:String,
    val cost:Double,
    val material:String
) {
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
    val quantityAvailable:Int = 0
}