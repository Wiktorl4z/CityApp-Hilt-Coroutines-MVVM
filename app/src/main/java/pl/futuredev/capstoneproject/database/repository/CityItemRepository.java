package pl.futuredev.capstoneproject.database.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import pl.futuredev.capstoneproject.database.dao.CityDao;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;

public class CityItemRepository {

    private CityDao cityDao;

    @Inject
    public CityItemRepository(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public Flowable<List<CityPOJO>> getAllCitiesRx() {
        return cityDao.getAllCitiesRx();
    }

    public LiveData<CityPOJO> getCityById(String cityId) {
        return cityDao.getCityById(cityId);
    }

    public void insertNewCity(CityPOJO cityPOJO) {
        cityDao.insertCity(cityPOJO);
    }

    public void deleteCity(CityPOJO cityPOJO) {
        cityDao.deleteCity(cityPOJO);
    }

    public List<CityPOJO> getAllCitiesSync() {
        return cityDao.getAllCities();
    }

    public Flowable<CityPOJO> getSelectedCityFromDatabase(String city){
        return cityDao.getSelectedCityFromDatabase(city);
    }

}
