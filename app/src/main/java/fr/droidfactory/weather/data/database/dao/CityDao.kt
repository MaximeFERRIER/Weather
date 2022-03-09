package fr.droidfactory.weather.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.droidfactory.weather.data.database.entities.CityDetailsResultEntity
import fr.droidfactory.weather.data.database.entities.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCity(cityEntity: CityEntity)

    @Query("SELECT * FROM CITY_ENTITY")
    abstract fun  observeCities(): Flow<List<CityDetailsResultEntity>>

    @Query("SELECT * FROM CITY_ENTITY WHERE NAME = :cityName")
    abstract fun  observeCityByName(cityName: String): Flow<CityDetailsResultEntity>
}