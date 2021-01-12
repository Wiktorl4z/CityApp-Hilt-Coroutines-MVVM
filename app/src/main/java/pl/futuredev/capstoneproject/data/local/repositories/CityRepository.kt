package pl.futuredev.capstoneproject.data.local.repositories

import androidx.lifecycle.LiveData
import pl.futuredev.capstoneproject.data.local.CityDao
import pl.futuredev.capstoneproject.data.local.entities.City
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val cityDao: CityDao
) {

    suspend fun insertCity(city: City) {
        cityDao.insertCity(city)
    }

    suspend fun deleteCityByName(name: String) {
        cityDao.deleteCityByName(name)
    }

    suspend fun getAllCities(): List<City> {
        return cityDao.getAllCities()
    }

   fun getCityByName(name: String): LiveData<City> {
        return cityDao.getCityByName(name)
    }

}