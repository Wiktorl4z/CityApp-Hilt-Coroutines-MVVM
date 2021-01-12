package pl.futuredev.capstoneproject.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.data.local.repositories.CityRepository

class CityViewModel @ViewModelInject constructor(
    private val repository: CityRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private var isLiked: LiveData<City>? = null

    fun isLikedCity(name: String): LiveData<City>? {
        if (isLiked == null) {
            isLiked = repository.getCityByName(name)
        }
        return isLiked
    }

    fun likeCity(city: City) {
        viewModelScope.launch(dispatcher) {
            repository.insertCity(city)
        }
    }

    fun removeFromLikedCity(name: String) {
        viewModelScope.launch(dispatcher) {
            repository.deleteCityByName(name)
        }
    }

}