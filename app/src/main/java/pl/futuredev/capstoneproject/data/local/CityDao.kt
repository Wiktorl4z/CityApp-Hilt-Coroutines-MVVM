package pl.futuredev.capstoneproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.others.Resource

@Dao
interface CityDao {

    @Query("SELECT * FROM city")
    suspend fun getAllCities(): List<City>

    @Query("SELECT * FROM city WHERE name =:name LIMIT 1")
    fun getCityByName(name:String): LiveData<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)

    @Query("DELETE FROM city WHERE name =:name")
    suspend fun deleteCityByName(name: String)

}