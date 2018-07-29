package pl.futuredev.capstoneproject.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import pl.futuredev.capstoneproject.database.entity.CityDataBase;

public class AddCityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final CityDataBase cityDataBase;
    private final int cityId;

    public AddCityViewModelFactory(CityDataBase cityDataBase, int cityId) {
        this.cityDataBase = cityDataBase;
        this.cityId = cityId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddCityViewModel(cityDataBase, cityId);
    }
}
