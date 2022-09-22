package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artisan_table")
class Artisan(
    val artisanName:String,
    val artisanPix:String,
    val artisanPhone:String,
    val artisanSkills:String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}