package pl.futuredev.capstoneproject.database.entity;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.converter.ImagesConverter;
import pl.futuredev.capstoneproject.database.dao.CityDao;

@Database(entities = {CityPOJO.class}, version = 6, exportSchema = false)
@TypeConverters({ImagesConverter.class})
public abstract class CityDataBase extends RoomDatabase {

    private static final String LOG_TAG = CityDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favouriteCity";
    private static CityDataBase cityDataBase;

    public static CityDataBase getInstance(Context context) {
        if (cityDataBase == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, context.getString(R.string.creating_new_database_instance));
                cityDataBase = Room.databaseBuilder(context.getApplicationContext(),
                        CityDataBase.class, CityDataBase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG, context.getString(R.string.getting_database_instance));
        return cityDataBase;
    }

    public abstract CityDao cityDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
