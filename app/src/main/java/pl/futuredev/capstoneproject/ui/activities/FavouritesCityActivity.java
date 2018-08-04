package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.entity.CityDataBase;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.APIService;
import pl.futuredev.capstoneproject.service.HttpConnector;
import pl.futuredev.capstoneproject.service.InternetReceiver;
import pl.futuredev.capstoneproject.ui.adapters.FavouritesCityAdapter;
import pl.futuredev.capstoneproject.ui.interfaces.IOnClickHandler;

public class FavouritesCityActivity extends AppCompatActivity implements IOnClickHandler {

    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE_STRING = "city_image_string";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";
    private static final String CITY_IMAGE = "city_image";

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @BindView(R.id.tv_no_fav_text)
    TextView tvNoFavText;
    @BindView(R.id.iv_no_city_fav)
    ImageView ivNoCityFav;
    private FavouritesCityAdapter cityResultAdapter;
    private List<Result> resultCity;
    private LinearLayoutManager linearLayoutManager;
    private CityDataBase cityDataBase;
    private InternetReceiver internetReceiver;
    private APIService service;
    private List<CityPOJO> cities;
    private boolean isFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_city);
        ButterKnife.bind(this);

        internetReceiver = new InternetReceiver();
        service = HttpConnector.getService(APIService.class);
        cityDataBase = CityDataBase.getInstance(getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        new CityDataBaseChecker().execute();
    }

    private void settingUpView() {
        if (isFavourite) {
            ivNoCityFav.setVisibility(View.INVISIBLE);
            tvNoFavText.setVisibility(View.INVISIBLE);
            cityResultAdapter = new FavouritesCityAdapter(cities, FavouritesCityActivity.this::onClick);
            ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(cityResultAdapter);
            scaleInAnimationAdapter.setDuration(1350);
            scaleInAnimationAdapter.setFirstOnly(false);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setLayoutManager(linearLayoutManager);
            myRecyclerView.setAdapter(scaleInAnimationAdapter);
        } else {
            myRecyclerView.setVisibility(View.INVISIBLE);
            ivNoCityFav.setVisibility(View.VISIBLE);
            tvNoFavText.setVisibility(View.VISIBLE);
            showToast("No cities in favourite");
        }
    }

    @Override
    public void onClick(int clickedItemIndex) {
        Intent intent = new Intent(FavouritesCityActivity.this, MainCityActivity.class);
        intent.putExtra(CITY_ID, cities.get(clickedItemIndex).getId());
        intent.putExtra(CITY_NAME, cities.get(clickedItemIndex).getCityName());
        intent.putExtra(CITY_SNIPPET, cities.get(clickedItemIndex).getSnippet());
        intent.putParcelableArrayListExtra(CITY_IMAGE, (ArrayList<? extends Parcelable>) cities.get(clickedItemIndex).getImages());
        startActivity(intent);
    }

    private class CityDataBaseChecker extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            cities = cityDataBase.cityDao().loadAllCitiesSync();
            if (cities.isEmpty()){
                isFavourite = false;
            } else {
                isFavourite = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            settingUpView();
        }
    }


    public void showToast(final String toast) {
        runOnUiThread(() -> Toast.makeText(this, toast, Toast.LENGTH_SHORT).show());
    }


}
