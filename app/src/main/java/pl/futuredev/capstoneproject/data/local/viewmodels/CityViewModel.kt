package pl.futuredev.capstoneproject.data.local.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.futuredev.capstoneproject.data.local.entities.City
import pl.futuredev.capstoneproject.data.local.repositories.CityRepository
import pl.futuredev.capstoneproject.others.Event
import pl.futuredev.capstoneproject.others.Resource

class CityViewModel @ViewModelInject constructor(
    private val cityRepository: CityRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _cities = MutableLiveData<Event<Resource<List<City>>>>()
    val cities: LiveData<Event<Resource<List<City>>>> = _cities

    fun insertCity(city: City) = viewModelScope.launch(dispatcher) {
        cityRepository.insertCity(city)
    }

    fun deleteCityByName(name: String) = viewModelScope.launch(dispatcher) {
        cityRepository.deleteCityByName(name)
    }

    fun getAllCities() {
        _cities.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            _cities.postValue(Event(Resource.Success(cityRepository.getAllCities())))
        }
    }

}