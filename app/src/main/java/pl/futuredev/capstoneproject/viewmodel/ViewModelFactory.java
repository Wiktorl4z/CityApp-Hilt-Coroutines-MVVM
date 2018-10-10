package pl.futuredev.capstoneproject.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.futuredev.capstoneproject.database.repository.CityItemRepository;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private CityItemRepository cityItemRepository;

    @Inject
    public ViewModelFactory(CityItemRepository cityItemRepository) {
        this.cityItemRepository = cityItemRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CityCollectionViewModel.class))
            return (T) new CityCollectionViewModel(cityItemRepository);
        else if (modelClass.isAssignableFrom(CityViewModel.class))
            return (T) new CityViewModel(cityItemRepository);
        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
