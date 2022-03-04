package fr.droidfactory.weather.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CityDetailsResultEntity(
    @Embedded
    val cityEntity: CityEntity,
    @Relation(
        parentColumn = "COLOR_ID",
        entityColumn = "_id"
    )
    val colorEntity: ColorEntity
)
