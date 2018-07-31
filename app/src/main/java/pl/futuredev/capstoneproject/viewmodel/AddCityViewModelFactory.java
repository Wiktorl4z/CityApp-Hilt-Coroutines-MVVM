package pl.futuredev.capstoneproject.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import pl.futuredev.capstoneproject.database.entity.CityDataBase;

public class AddCityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final CityDataBase cityDataBase;
    private final String cityName;

    public AddCityViewModelFactory(CityDataBase cityDataBase, String cityName) {
        this.cityDataBase = cityDataBase;
        this.cityName = cityName;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddCityViewModel(cityDataBase, cityName);
    }
}
