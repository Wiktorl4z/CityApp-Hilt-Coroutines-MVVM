package pl.futuredev.capstoneproject.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.futuredev.capstoneproject.data.remote.entities.Result
import pl.futuredev.capstoneproject.others.Event
import pl.futuredev.capstoneproject.others.Resource
import pl.futuredev.capstoneproject.repositories.CityRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: CityRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _cities = MutableLiveData<Event<Resource<List<Result>>>>()
    val cities: LiveData<Event<Resource<List<Result>>>> = _cities

    fun getCitiesByName(name: String) {
        _cities.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            val respond = repository.getCityByNameFromApi(name)
            _cities.postValue(Event(respond))
        }
    }

}



