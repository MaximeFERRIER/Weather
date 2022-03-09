package fr.droidfactory.weather.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.droidfactory.weather.data.database.dao.CityDao
import fr.droidfactory.weather.data.database.dao.CityInfoDao
import fr.droidfactory.weather.data.database.dao.ColorDao
import fr.droidfactory.weather.data.database.entities.CityEntity
import fr.droidfactory.weather.data.database.entities.CityInfoEntity
import fr.droidfactory.weather.data.database.entities.ColorEntity
import fr.droidfactory.weather.data.database.migrations.Migration1To2
import fr.droidfactory.weather.data.database.migrations.Migration2To3

@Database(
    entities = [
        CityEntity::class,
        ColorEntity::class,
        CityInfoEntity::class
    ],
    version = 3,
    exportSchema = true
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun cityInfoDao(): CityInfoDao
    abstract fun colorDao(): ColorDao

    internal companion object {
        private const val DATABASE_NAME = "Weather-app.db"
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, WeatherDatabase::class.java, DATABASE_NAME)
                        .addMigrations(Migration1To2())
                        .addMigrations(Migration2To3())
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}