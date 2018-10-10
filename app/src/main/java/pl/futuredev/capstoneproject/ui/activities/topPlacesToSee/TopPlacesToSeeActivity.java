package pl.futuredev.capstoneproject.ui.activities.topPlacesToSee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.ui.activities.BaseActivity;

public class TopPlacesToSeeActivity extends BaseActivity {

    private static final String TAG = TopPlacesToSeeActivity.class.getSimpleName();
    private static final String CITY_ID = "city_id";
    private String cityId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_places_to_see_a);

        Intent intent = getIntent();
        cityId = intent.getStringExtra(CITY_ID);

        FragmentManager manager = getSupportFragmentManager();

        TopPlacesToSeeFragment fragment = (TopPlacesToSeeFragment) manager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = TopPlacesToSeeFragment.newInstance(cityId);
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_top_places_to_see,
                TAG
        );
    }
}

