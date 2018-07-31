package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    @BindView(R.id.bt_test_top_places_to_see)
    Button btTestTopPlacesToSee;
    @BindView(R.id.bt_test_top_places_to_eat)
    Button btTestTopPlacesToEat;
    @BindView(R.id.bt_test_top_scoring_tags)
    Button btTestTopScoringTags;
    @BindView(R.id.ThreeTwoImage)
    ThreeTwoImageView threeTwoImage;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tv_city_main_snippet)
    TextView tvCityMainSnippet;
    @BindView(R.id.tv_city_main_name)
    TextView tvCityMainName;

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
    private static boolean isFavourite;


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


        settingUpView();
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();

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
                    .parseColor("#ff0000")));
        } else {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#cdf7fb")));
        }
    }

    public void checkingObjectInDataBase() {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                CityPOJO city = cityDataBase.cityDao().loadCityByNameCityPOJO(cityId);
                if (city != null) {
                    removeFromDatabase(city);
                    isFavourite = false;
                } else {
                    addToDatabase();
                    isFavourite = true;
                }
            }
        });
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
        cityPOJO = new CityPOJO(cityId);
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                cityDataBase.cityDao().insertCity(cityPOJO);
                showToast(getString(R.string.add_city_to_db));
            }
        });
    }

    public void showToast(final String toast) {
        runOnUiThread(() -> Toast.makeText(this, toast, Toast.LENGTH_SHORT).show());
    }

    private void settingUpView() {
        if (imageList != null && !imageList.isEmpty()) {
            Original originalImage = imageList.get(0).getSizes().getOriginal();
            Picasso.get()
                    .load(originalImage.getUrl())
                    .into(threeTwoImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.get().load(originalImage.getUrl()).into(threeTwoImage);
                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(R.drawable.rest1).into(threeTwoImage);
                        }
                    });
        }
        tvCityMainSnippet.setText(citySnippet);
        tvCityMainName.setText(cityName);
    }

    private class FabColorChecker extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            CityPOJO city = cityDataBase.cityDao().loadCityByNameCityPOJO(cityId);
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
        }
    }
}
