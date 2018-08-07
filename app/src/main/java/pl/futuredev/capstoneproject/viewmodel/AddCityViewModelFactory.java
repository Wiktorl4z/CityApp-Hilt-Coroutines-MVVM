package pl.futuredev.capstoneproject.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import pl.futuredev.capstoneproject.database.entity.CityDataBase;

public class AddCityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final CityDataBase cityDataBase;
    private final String name;

    public AddCityViewModelFactory(CityDataBase cityDataBase, String name) {
        this.cityDataBase = cityDataBase;
        this.name = name;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddCityViewModel(cityDataBase, name);
    }
}
