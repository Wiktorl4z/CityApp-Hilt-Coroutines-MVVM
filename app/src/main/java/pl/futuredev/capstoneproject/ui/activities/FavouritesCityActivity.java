package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.entity.CityDataBase;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.ui.adapters.CityResultAdapter;
import pl.futuredev.capstoneproject.ui.interfaces.IOnClickHandler;

public class FavouritesCityActivity extends AppCompatActivity implements IOnClickHandler {

    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE = "city_image";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    private CityResultAdapter cityResultAdapter;
    private List<Result> resultCity;
    private LinearLayoutManager linearLayoutManager;
    private CityDataBase cityDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_city);
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultCity = getIntent().getParcelableArrayListExtra("city");
        cityResultAdapter = new CityResultAdapter(resultCity, FavouritesCityActivity.this::onClick);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(cityResultAdapter);
        scaleInAnimationAdapter.setDuration(1350);
        scaleInAnimationAdapter.setFirstOnly(false);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(scaleInAnimationAdapter);
    }

    @Override
    public void onClick(int clickedItemIndex) {
        Intent intent = new Intent(FavouritesCityActivity.this, MainCityActivity.class);
        intent.putExtra(CITY_ID, resultCity.get(clickedItemIndex).getId());
        intent.putExtra(CITY_NAME, resultCity.get(clickedItemIndex).getName());
        intent.putParcelableArrayListExtra(CITY_IMAGE, (ArrayList<? extends Parcelable>) resultCity.get(clickedItemIndex).getImages());
        intent.putExtra(CITY_SNIPPET, resultCity.get(clickedItemIndex).getSnippet());
        startActivity(intent);
    }
}
