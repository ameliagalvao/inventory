package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients_table")
data class Clients(
    val businessName:String,
    val address:String = "",
    val contactPerson:String = "",
    val contactPhone:String = "",
    val cpfOrCnpj:String = "",
    val email:String = ""
) {
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
}