package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.entity.CityDataBase;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;
import pl.futuredev.capstoneproject.models.Image;
import pl.futuredev.capstoneproject.models.Original;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.APIService;
import pl.futuredev.capstoneproject.service.HttpConnector;
import pl.futuredev.capstoneproject.service.InternetReceiver;
import pl.futuredev.capstoneproject.viewmodel.AppExecutors;

public class MainCityActivity extends AppCompatActivity {

    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE = "city_image";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";
    private static final String CITY_IMAGE_STRING = "city_image_string";

    @BindView(R.id.bt_test_top_places_to_see)
    Button btTestTopPlacesToSee;
    @BindView(R.id.bt_test_top_places_to_eat)
    Button btTestTopPlacesToEat;
    @BindView(R.id.bt_test_top_scoring_tags)
    Button btTestTopScoringTags;
    @BindView(R.id.threeTwoImage)
    ThreeTwoImageView threeTwoImage;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tv_city_main_snippet)
    TextView tvCityMainSnippet;
    @BindView(R.id.tv_city_main_name)
    TextView tvCityMainName;
    @BindView(R.id.toolbarId)
    Toolbar toolbarId;
    @BindView(R.id.action_up)
    ImageButton actionUp;

    private Image image;
    private InternetReceiver internetReceiver;
    private APIService service;
    private List<Result> resultList;
    private List<Image> imageList;
    private String cityId;
    private String citySnippet;
    private String cityName;
    private CityDataBase cityDataBase;
    private CityPOJO cityPOJO;
    private boolean isFavourite;
    private Original originalImage;
    private String originalImageUrl;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_main);
        ButterKnife.bind(this);

        internetReceiver = new InternetReceiver();
        service = HttpConnector.getService(APIService.class);
        Intent intent = getIntent();
        cityId = intent.getStringExtra(CITY_ID);
        cityName = intent.getStringExtra(CITY_NAME);
        citySnippet = getIntent().getStringExtra(CITY_SNIPPET);
        imageList = intent.getParcelableArrayListExtra(CITY_IMAGE);

        cityDataBase = CityDataBase.getInstance(getApplicationContext());

        if (imageList != null && !imageList.isEmpty()) {
            originalImage = imageList.get(0).getSizes().getOriginal();
            originalImageUrl = originalImage.getUrl();
        } else {
            originalImageUrl = "";
        }
        new FabColorChecker().execute();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        checkingObjectInDataBase();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        colorSwitcherForFAB();
                    }
                }.execute();
            }
        });


        btTestTopPlacesToSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCityActivity.this, TopPlacesToSeeActivity.class);
                intent.putExtra(CITY_ID, cityId);
                startActivity(intent);
            }
        });

        btTestTopPlacesToEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCityActivity.this, TopPlacesToEatActivity.class);
                intent.putExtra(CITY_ID, cityId);
                startActivity(intent);
            }
        });

        btTestTopScoringTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCityActivity.this, TopScoringTagForLocationActivity.class);
                intent.putExtra(CITY_ID, cityId);
                startActivity(intent);
            }
        });

    }

    private void colorSwitcherForFAB() {
        if (isFavourite) {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#087f23")));
        } else {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#c2c1c1")));
        }
    }

    public void checkingObjectInDataBase() {
        CityPOJO city = cityDataBase.cityDao().loadCityByNameCityPOJO(cityName);
        if (city != null) {
            removeFromDatabase(city);
            isFavourite = false;
        } else {
            addToDatabase();
            isFavourite = true;
        }
    }

    public void removeFromDatabase(CityPOJO city) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                cityDataBase.cityDao().deleteCity(city);
                showToast(getString(R.string.city_removed_from_db));
            }
        });
    }

    public void addToDatabase() {
        cityPOJO = new CityPOJO(cityName, citySnippet, imageList, cityId);
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                cityDataBase.cityDao().insertCity(cityPOJO);
                showToast(getString(R.string.add_city_to_db));
            }
        });
    }

    private void settingUpView() {
        Picasso.get()
                .load(originalImageUrl)
                .into(threeTwoImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.get().load(originalImageUrl).into(threeTwoImage);
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(R.drawable.rest1).into(threeTwoImage);
                    }
                });

        tvCityMainSnippet.setText(citySnippet);
        tvCityMainName.setText(cityName);
        actionUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });
    }

    private class FabColorChecker extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            CityPOJO city = cityDataBase.cityDao().loadCityByNameCityPOJO(cityName);
            if (city != null) {
                isFavourite = true;
            } else {
                isFavourite = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            colorSwitcherForFAB();
            settingUpView();
        }
    }

    public void showToast(final String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
    }

    protected void onPause() {
        if (toast != null)
            toast.cancel();
        super.onPause();
    }
}
