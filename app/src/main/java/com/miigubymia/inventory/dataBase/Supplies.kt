package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "supplies_table")
data class Supplies(
    val name:String,
    val kind:String,
    val cost:Double,
    val material:String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    var quantityAvailable:Int = 0
    //color
}