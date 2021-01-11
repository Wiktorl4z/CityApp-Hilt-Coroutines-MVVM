package pl.futuredev.capstoneproject.data.local.repositories

import pl.futuredev.capstoneproject.data.local.CityDao
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.others.Resource
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val cityDao: CityDao
) {

    suspend fun insertCity(city: City) {
        cityDao.insertCity(city)
    }

    suspend fun deleteCity(city: City) {
        cityDao.deleteCity(city)
    }

    suspend fun getAllCities(): List<City> {
        return cityDao.getAllCities()
    }

}