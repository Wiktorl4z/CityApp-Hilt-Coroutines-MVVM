package pl.futuredev.capstoneproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.futuredev.capstoneproject.data.local.converters.ImagesConverter
import pl.futuredev.capstoneproject.data.local.entities.City

@Database(entities = [City::class], version = 1, exportSchema = false)
@TypeConverters(ImagesConverter::class)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
