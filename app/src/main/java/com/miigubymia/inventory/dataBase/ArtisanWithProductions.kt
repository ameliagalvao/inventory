package com.miigubymia.inventory.dataBase

import androidx.room.Embedded
import androidx.room.Relation

data class ArtisanWithProductions(
    @Embedded val artisan:Artisan,
    @Relation(
        parentColumn = "id",
        entityColumn = "artisanId"
    )
    val productions:List<Production>
) {

}