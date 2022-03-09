package fr.droidfactory.weather.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import fr.droidfactory.weather.data.database.entities.CityInfoEntity

@Dao
abstract class CityInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCityInfo(cityInfoEntity: CityInfoEntity)
}