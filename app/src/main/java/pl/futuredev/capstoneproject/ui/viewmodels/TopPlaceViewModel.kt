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
import pl.futuredev.capstoneproject.others.Constants
import pl.futuredev.capstoneproject.others.Event
import pl.futuredev.capstoneproject.others.Resource
import pl.futuredev.capstoneproject.repositories.CityRepository

class TopPlaceViewModel @ViewModelInject constructor(
    private val cityRepository: CityRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _city = MutableLiveData<Event<Resource<List<Result>>>>()
    val city: LiveData<Event<Resource<List<Result>>>> = _city

    fun getValue(request: String, city: String) {
        _city.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            when (request) {
                Constants.SCORING -> {
                    val respond = cityRepository.getTopScoredTagsForLocation(city)
                    _city.postValue(Event(respond))
                }
                Constants.PLACES_EAT -> {
                    val respond = cityRepository.getTopPlacesToEat(city)
                    _city.postValue(Event(respond))
                }
                Constants.PLACES_TO_SEE -> {
                    val respond = cityRepository.getTopPlacesToSee(city)
                    _city.postValue(Event(respond))
                }
            }
        }
    }

}