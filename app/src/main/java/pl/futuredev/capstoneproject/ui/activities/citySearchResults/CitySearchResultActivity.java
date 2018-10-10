package pl.futuredev.capstoneproject.ui.activities.citySearchResults;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import java.util.List;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.ui.activities.BaseActivity;

public class CitySearchResultActivity extends BaseActivity {

    private static final String TAG = CitySearchResultActivity.class.getSimpleName();
    private List<Result> resultCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_search_result_a);

        resultCity = getIntent().getParcelableArrayListExtra("city");

        FragmentManager manager = getSupportFragmentManager();

        CitySearchResultFragment fragment = (CitySearchResultFragment) manager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = CitySearchResultFragment.newInstance(resultCity);
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_city_search,
                TAG
        );
    }
}
