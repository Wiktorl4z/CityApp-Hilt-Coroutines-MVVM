package pl.futuredev.capstoneproject.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pl.futuredev.capstoneproject.database.entity.CityPOJO;

@Dao
public interface CityDao {

    @Query("SELECT * FROM city")
    LiveData<List<CityPOJO>> loadAllCities();

    @Query("SELECT * FROM city")
    List<CityPOJO> loadAllCitiesSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(CityPOJO cityPOJO);

    @Delete
    void deleteCity(CityPOJO cityPOJO);

    @Query("SELECT * FROM city WHERE name = :name")
    LiveData<CityPOJO> loadCityById(String name);

    @Query("SELECT * FROM city WHERE name = :name")
    CityPOJO loadCityByNameCityPOJO(String name);

}
