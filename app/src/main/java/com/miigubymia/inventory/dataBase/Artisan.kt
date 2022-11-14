package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "artisan_table")
class Artisan(
    @PrimaryKey(autoGenerate = false)
    val artisanName:String,
    val artisanPix:String,
    val artisanPhone:String,
    val artisanSkills:String):Serializable {
    var artisanProduction = ""
}