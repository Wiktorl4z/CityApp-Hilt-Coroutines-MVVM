package pl.futuredev.capstoneproject.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pl.futuredev.capstoneproject.data.local.entities.City


@Dao
interface CityDao {

    /*  @Query("SELECT * FROM city")
      fun getAllCitiesRx(): Flowable<List<CityPOJO?>?>?

      @Query("SELECT * FROM city WHERE name = :name")
      fun getCityById(name: String?): LiveData<CityPOJO?>?

      @Query("SELECT * FROM city")
      fun getAllCities(): List<CityPOJO?>?

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertCity(cityPOJO: CityPOJO?)

      @Delete
      fun deleteCity(cityPOJO: CityPOJO?)

      @Query("SELECT * FROM city WHERE name = :name")
      fun getSelectedCityFromDatabase(name: String?): Flowable<CityPOJO?>?*/


    @Query("SELECT * FROM city")
    suspend fun getCities(): Flow<List<City>>

    @Query("SELECT * FROM city WHERE name = :name")
    suspend fun getCityByName(name: String): City

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: City)

    @Delete
    fun deleteCity(city: City)

}