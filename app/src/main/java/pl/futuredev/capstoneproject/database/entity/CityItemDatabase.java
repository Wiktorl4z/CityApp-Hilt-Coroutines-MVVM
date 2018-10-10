package pl.futuredev.capstoneproject.database.entity;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import pl.futuredev.capstoneproject.database.converter.ImagesConverter;
import pl.futuredev.capstoneproject.database.dao.CityDao;


@Database(entities = {CityPOJO.class}, version = 1, exportSchema = false)
@TypeConverters({ImagesConverter.class})
public abstract class CityItemDatabase extends RoomDatabase {

    public abstract CityDao cityDao();

}
