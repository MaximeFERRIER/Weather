package fr.droidfactory.weather.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlin.math.round

@Entity(
    tableName = "CITY_INFO_ENTITY",
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["NAME"],
            childColumns = ["CITY_NAME"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CityInfoEntity(
    @PrimaryKey @ColumnInfo(name = "CITY_NAME") val cityName: String,
    @ColumnInfo(name = "UPDATE_DATE") val updateDate: String,
    @ColumnInfo(name = "TEMPERATURE") val temperature: Double,
    @ColumnInfo(name = "CONDITION_ICON") val conditionIcon: String,
    @ColumnInfo(name = "CONDITION_TEXT") val conditionText: String,
    @ColumnInfo(name = "HUMIDITY") val humidity : Int,
    @ColumnInfo(name = "TEMPERATURE_FEEL_LIKE") val temperatureFeelLike: Double,
    @ColumnInfo(name = "UV") val uv: Double
) {

    val temperatureF: Double get() = round(temperature.toFahrenheit())
    val temperatureFeelLikeF: Double get() = round(temperatureFeelLike.toFahrenheit())
    private fun Double.toFahrenheit() = this * 1.8 + 32.0
}
