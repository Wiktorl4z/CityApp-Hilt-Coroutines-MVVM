package pl.futuredev.capstoneproject.di.module;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.database.dao.CityDao;
import pl.futuredev.capstoneproject.database.entity.CityItemDatabase;
import pl.futuredev.capstoneproject.database.repository.CityItemRepository;
import pl.futuredev.capstoneproject.viewmodel.ViewModelFactory;

@Module
public class RoomModule {

    private final CityItemDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                CityItemDatabase.class,
                "CityItem.db"
        ).build();
    }

    @Provides
    @Singleton
    CityItemRepository provideCityListRepository(CityDao cityDao) {
        return new CityItemRepository(cityDao);
    }

    @Provides
    @Singleton
    CityDao provideCityItemDao(CityItemDatabase database) {
        return database.cityDao();
    }

    @Provides
    @Singleton
    CityItemDatabase provideListItemDatabase(Application application) {
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(CityItemRepository cityItemRepository) {
        return new ViewModelFactory(cityItemRepository);
    }
}
