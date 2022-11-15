package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "production_table",
foreignKeys = [ForeignKey(
    entity = Artisan::class,
    parentColumns = ["artisanID"],
    childColumns = ["artisanID"]
)])
data class Production(
    val artisanName:String,
    val productName:String,
    val productionQuantity:Int,
    val date:String,
    var artisanID:Int
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var productionID:Int = 0
}