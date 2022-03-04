package fr.droidfactory.weather.data.database.entities

import androidx.room.*

@Entity(
    tableName = "CITY_ENTITY",
    indices = [Index("COLOR_ID")],
    foreignKeys = [
        ForeignKey(
            entity = ColorEntity::class,
            parentColumns = ["_id"],
            childColumns = ["COLOR_ID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CityEntity(
    @PrimaryKey @ColumnInfo(name = "NAME") val name: String,
    @ColumnInfo(name = "COLOR_ID") val colorId: Long
)
