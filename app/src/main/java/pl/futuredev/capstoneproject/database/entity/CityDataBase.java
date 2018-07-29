package pl.futuredev.capstoneproject.database.entity;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.dao.CityDao;

@Database(entities = {CityPOJO.class}, version = 1, exportSchema = false)
public abstract class CityDataBase extends RoomDatabase {

    private static final String LOG_TAG = CityDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favouriteCity";
    private static CityDataBase recipeDataBaseInstance;

    public static CityDataBase getInstance(Context context) {
        if (recipeDataBaseInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, context.getString(R.string.creating_new_database_instance));
                recipeDataBaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        CityDataBase.class, CityDataBase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG, context.getString(R.string.getting_database_instance));
        return recipeDataBaseInstance;
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
