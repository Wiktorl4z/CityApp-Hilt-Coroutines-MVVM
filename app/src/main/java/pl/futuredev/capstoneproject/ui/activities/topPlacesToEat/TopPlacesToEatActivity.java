package pl.futuredev.capstoneproject.ui.activities.topPlacesToEat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.ui.activities.BaseActivity;

public class TopPlacesToEatActivity extends BaseActivity {

    private static final String TAG = TopPlacesToEatActivity.class.getSimpleName();
    private static final String CITY_ID = "city_id";
    private String cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_places_to_eat_a);

        Intent intent = getIntent();
        cityId = intent.getStringExtra(CITY_ID);

        FragmentManager manager = getSupportFragmentManager();

        TopPlacesToEatFragment fragment = (TopPlacesToEatFragment) manager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = TopPlacesToEatFragment.newInstance(cityId);
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_top_places_to_eat,
                TAG
        );
    }
}