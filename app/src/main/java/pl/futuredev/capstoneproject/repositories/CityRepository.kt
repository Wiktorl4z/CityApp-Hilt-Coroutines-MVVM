package pl.futuredev.capstoneproject.repositories

import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.futuredev.capstoneproject.data.local.CityDao
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.data.remote.CityApi
import pl.futuredev.capstoneproject.others.Resource
import pl.futuredev.capstoneproject.others.safeCall
import javax.inject.Inject

@ActivityScoped
class CityRepository @Inject constructor(
    private val cityApi: CityApi
) {

    suspend fun getCityByNameFromApi(name: String) = withContext(Dispatchers.IO) {
        safeCall {
            val response = cityApi.getCityByNameFromApi("trigram:$name")
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    Resource.Success(response.body()!!.results)
                } ?: Resource.Error("City not found", null)
            } else {
                Resource.Error("Couldn't fetch data", null)
            }
        }
    }

    suspend fun getTopPlacesToSee(locationId: String) = withContext(Dispatchers.IO) {
        safeCall {
            val response = cityApi.getTopPlacesToSee(locationId)
            if (response.isSuccessful) {
                Resource.Success(response)
            } else {
                Resource.Error("Couldn't fetch data", null)
            }
        }
    }

    suspend fun getTopPlacesToEat(locationId: String) {
        safeCall {
            val response = cityApi.getTopPlacesToEat(locationId)
            if (response.isSuccessful) {
                Resource.Success(response)
            } else {
                Resource.Error("Couldn't fetch data", null)
            }
        }
    }

    suspend fun getTopScoredTagsForLocation(locationId: String) {
        safeCall {
            val response = cityApi.getTopScoredTagsForLocation(locationId)
            if (response.isSuccessful) {
                Resource.Success(response)
            } else {
                Resource.Error("Couldn't fetch data", null)
            }
        }
    }

/*    suspend fun getCities() {
        cityDao.getCities()
    }

    suspend fun getCityByName(name: String) {
        cityDao.getCityByName(name)
    }

    suspend fun insertCity(city: City) {
        cityDao.insertCity(city)
    }

    suspend fun deleteCity(city: City) {
        cityDao.deleteCity(city)
    }*/
}