package pl.futuredev.capstoneproject.ui.activities.favCity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.ui.activities.BaseActivity;

public class FavCityActivity extends BaseActivity {

    private static final String TAG = FavCityActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_city_a);

        FragmentManager manager = getSupportFragmentManager();
        FavCityFragment fragment = (FavCityFragment) manager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = new FavCityFragment();
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_fav_city,
                TAG
        );
    }
}
