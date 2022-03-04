package fr.droidfactory.weather.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import fr.droidfactory.weather.data.database.entities.ColorEntity

@Dao
abstract class ColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertColor(colorEntity: ColorEntity): Long
}