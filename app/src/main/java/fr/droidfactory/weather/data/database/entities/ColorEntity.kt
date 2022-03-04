package fr.droidfactory.weather.data.database.entities

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COLOR_ENTITY")
data class ColorEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val _id: Long = 0,
    @ColumnInfo(name = "RED") val red: Int,
    @ColumnInfo(name = "GREEN") val green: Int,
    @ColumnInfo(name = "BLUE") val blue: Int
) {
    val color get() = Color(red, green, blue)
}
