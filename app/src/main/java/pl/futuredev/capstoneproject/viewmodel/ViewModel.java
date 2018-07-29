package pl.futuredev.capstoneproject.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.entity.CityDataBase;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;

public class ViewModel extends AndroidViewModel {

    private static final String TAG = ViewModel.class.getSimpleName();
    private LiveData<List<CityPOJO>> listCityPOJO;

    public ViewModel(@NonNull Application application) {
        super(application);
        CityDataBase cityDataBase = CityDataBase.getInstance(this.getApplication());
        Log.d(TAG, application.getString(R.string.retrieving_recipes_from_db));
        listCityPOJO = cityDataBase.cityDao().loadAllCities();
    }

    public LiveData<List<CityPOJO>> getListCityPOJO() {
        return listCityPOJO;
    }
}
