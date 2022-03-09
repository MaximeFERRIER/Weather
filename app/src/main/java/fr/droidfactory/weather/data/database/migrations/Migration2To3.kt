package fr.droidfactory.weather.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration2To3: Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        migrateCityInfos(database)
    }

    private fun migrateCityInfos(database: SupportSQLiteDatabase) {
        with(database) {
            execSQL("""
                CREATE TABLE IF NOT EXISTS `CITY_INFO_ENTITY` (
                    `CITY_NAME` TEXT NOT NULL, 
                    `UPDATE_DATE` TEXT NOT NULL, 
                    `TEMPERATURE` REAL NOT NULL, 
                    `CONDITION_ICON` TEXT NOT NULL, 
                    `CONDITION_TEXT` TEXT NOT NULL, 
                    `HUMIDITY` INTEGER NOT NULL, 
                    `TEMPERATURE_FEEL_LIKE` REAL NOT NULL, 
                    `UV` REAL NOT NULL, 
                    PRIMARY KEY(`CITY_NAME`), 
                    FOREIGN KEY(`CITY_NAME`) REFERENCES `CITY_ENTITY`(`NAME`) ON UPDATE NO ACTION ON DELETE CASCADE )
            """)
        }
    }
}