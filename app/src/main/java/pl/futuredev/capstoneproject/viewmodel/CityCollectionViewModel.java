package pl.futuredev.capstoneproject.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Flowable;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;
import pl.futuredev.capstoneproject.database.repository.CityItemRepository;

public class CityCollectionViewModel extends ViewModel {

    private CityItemRepository cityItemRepository;

    public CityCollectionViewModel(CityItemRepository cityItemRepository) {
        this.cityItemRepository = cityItemRepository;
    }

    public Flowable<List<CityPOJO>> getAllCitiesRx() {
        return cityItemRepository.getAllCitiesRx();
    }

    public Flowable<CityPOJO> getSelectedCityFromDatabase(String city) {
        return cityItemRepository.getSelectedCityFromDatabase(city);
    }

    public void deleteCityFromList(CityPOJO cityPOJO) {
        cityItemRepository.deleteCity(cityPOJO);
    }

    public void addNewCityToDatabase(CityPOJO cityPOJO) {
        cityItemRepository.insertNewCity(cityPOJO);
    }

}
