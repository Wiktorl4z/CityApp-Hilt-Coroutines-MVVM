package pl.futuredev.capstoneproject.data.local

import androidx.room.*
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.others.Resource

@Dao
interface CityDao {

    @Query("SELECT * FROM city")
    suspend fun getAllCities(): List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)

    @Delete
    suspend fun deleteCity(city: City)

}