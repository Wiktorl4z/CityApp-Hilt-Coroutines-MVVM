package pl.futuredev.capstoneproject.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import pl.futuredev.capstoneproject.database.entity.CityDataBase;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;

public class AddCityViewModel extends ViewModel {

    private LiveData<CityPOJO> city;

    public AddCityViewModel(CityDataBase cityDataBase, int cityId) {
        this.city = cityDataBase.cityDao().loadCityById(cityId);
    }

    public LiveData<CityPOJO> getCity() {
        return city;
    }
}
