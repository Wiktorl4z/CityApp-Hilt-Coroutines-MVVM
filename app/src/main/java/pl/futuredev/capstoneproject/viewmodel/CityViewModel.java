package pl.futuredev.capstoneproject.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import pl.futuredev.capstoneproject.database.entity.CityPOJO;
import pl.futuredev.capstoneproject.database.repository.CityItemRepository;

public class CityViewModel extends ViewModel {

    private CityItemRepository cityItemRepository;

    public CityViewModel(CityItemRepository cityItemRepository) {
        this.cityItemRepository = cityItemRepository;
    }

    public LiveData<CityPOJO> getCityById(String cityId) {
        return cityItemRepository.getCityById(cityId);
    }

}
