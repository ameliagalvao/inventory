package com.miigubymia.inventory.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "clients_table")
data class Clients(
    val businessName:String,
    val address:String = "",
    val contactPerson:String = "",
    val contactPhone:String = "",
    val cpfOrCnpj:String = "",
    val email:String = ""
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    // openOrders
    // concludedOrders
}