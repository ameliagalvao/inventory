package com.miigubymia.inventory.dataBase

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithProductions(
    @Embedded val product:Products,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val productions: List<Production>
) {
}