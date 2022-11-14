package com.miigubymia.inventory.dataBase

import androidx.room.Embedded
import androidx.room.Relation

data class ArtisanWithProductions(
    @Embedded val artisan:Artisan,
    @Relation(
        parentColumn = "artisanName",
        entityColumn = "artisanName"
    )
    val productions:List<Production>
) {

}