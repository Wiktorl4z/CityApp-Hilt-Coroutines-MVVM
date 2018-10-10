package pl.futuredev.capstoneproject.ui.activities.topLocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.ui.activities.BaseActivity;

public class TopScoringTagForLocationActivity extends BaseActivity {

    private static final String TAG = TopScoringTagForLocationActivity.class.getSimpleName();
    private static final String CITY_ID = "city_id";
    private String cityId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scoring_tag_for_location_a);

        Intent intent = getIntent();
        cityId = intent.getStringExtra(CITY_ID);

        FragmentManager manager = getSupportFragmentManager();

        TopScoringTagForLocationFragment fragment = (TopScoringTagForLocationFragment) manager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = TopScoringTagForLocationFragment.newInstance(cityId);
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_top_scoring_tag_for_location,
                TAG
        );
    }
}