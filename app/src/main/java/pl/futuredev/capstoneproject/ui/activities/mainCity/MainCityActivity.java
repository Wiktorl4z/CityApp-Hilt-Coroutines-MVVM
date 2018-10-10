package pl.futuredev.capstoneproject.ui.activities.mainCity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import java.util.List;

import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Image;
import pl.futuredev.capstoneproject.ui.activities.BaseActivity;

public class MainCityActivity extends BaseActivity {

    private static final String TAG = MainCityActivity.class.getSimpleName();
    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE = "city_image";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";
    private static final String LIST_FRAG = "LIST_FRAG";
    private List<Image> imageList;
    private String cityId;
    private String citySnippet;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_city_a);

        Intent intent = getIntent();
        cityId = intent.getStringExtra(CITY_ID);
        cityName = intent.getStringExtra(CITY_NAME);
        citySnippet = getIntent().getStringExtra(CITY_SNIPPET);
        imageList = intent.getParcelableArrayListExtra(CITY_IMAGE);

        FragmentManager manager = getSupportFragmentManager();

        MainCityFragment fragment = (MainCityFragment) manager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = MainCityFragment.newInstance(cityId, cityName, citySnippet, imageList);
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_main_city,
                TAG
        );
    }
}