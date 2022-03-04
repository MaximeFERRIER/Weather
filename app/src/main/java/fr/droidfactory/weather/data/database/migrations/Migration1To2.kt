package fr.droidfactory.weather.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To2: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        migrateColors(database)
        migrateCities(database)
    }

    private fun migrateColors(database: SupportSQLiteDatabase) {
        with(database) {
            execSQL(
                """
                   CREATE TABLE IF NOT EXISTS `COLOR_ENTITY` (
                    `_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                    `RED` INTEGER NOT NULL, 
                    `GREEN` INTEGER NOT NULL, 
                    `BLUE` INTEGER NOT NULL) 
                """)

            execSQL("""
               INSERT INTO `COLOR_ENTITY` (`_id`, `RED`, `GREEN`, `BLUE`) VALUES (1, ${(0..255).random()}, ${(0..255).random()}, ${(0..255).random()}) 
            """)
        }
    }

    private fun migrateCities(database: SupportSQLiteDatabase) {
        with(database) {
            execSQL("""
               CREATE TABLE IF NOT EXISTS `TEMP_TABLE` (
                `NAME` TEXT NOT NULL, 
                `COLOR_ID` INTEGER NOT NULL, 
                PRIMARY KEY(`NAME`), 
                FOREIGN KEY(`COLOR_ID`) REFERENCES `COLOR_ENTITY`(`_id`) ON UPDATE NO ACTION ON DELETE CASCADE ) 
            """)

            execSQL(
                """
                   CREATE INDEX IF NOT EXISTS `index_CITY_ENTITY_COLOR_ID` ON `TEMP_TABLE` (`COLOR_ID`) 
                """)

            execSQL(
                """
                   INSERT INTO `TEMP_TABLE` (`NAME`, `COLOR_ID`) SELECT `NAME`, 1 FROM `CITY_ENTITY` 
                """
            )
            execSQL(
                """
                   DROP TABLE `CITY_ENTITY` 
                """
            )

            execSQL(
                """
                   ALTER TABLE `TEMP_TABLE` RENAME TO `CITY_ENTITY` 
                """
            )
        }
    }
}