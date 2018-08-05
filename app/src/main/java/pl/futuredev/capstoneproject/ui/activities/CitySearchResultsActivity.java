package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.ui.adapters.CityResultAdapter;
import pl.futuredev.capstoneproject.ui.interfaces.IOnClickHandler;

public class CitySearchResultsActivity extends AppCompatActivity implements CityResultAdapter.ItemClickCallback {

    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE = "city_image";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @BindView(R.id.iv_no_city)
    ImageView ivNoCity;
    @BindView(R.id.tv_no_found_city)
    TextView tvNoFoundCity;

    private CityResultAdapter cityResultAdapter;
    private List<Result> resultCity;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_result);
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultCity = getIntent().getParcelableArrayListExtra("city");
        if (resultCity.isEmpty()) {
            myRecyclerView.setVisibility(View.INVISIBLE);
            tvNoFoundCity.setVisibility(View.VISIBLE);
            ivNoCity.setVisibility(View.VISIBLE);
        } else {
            myRecyclerView.setVisibility(View.VISIBLE);
            tvNoFoundCity.setVisibility(View.INVISIBLE);
            ivNoCity.setVisibility(View.INVISIBLE);
            cityResultAdapter = new CityResultAdapter(resultCity, CitySearchResultsActivity.this::onItemClick);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setLayoutManager(linearLayoutManager);
            myRecyclerView.setAdapter(cityResultAdapter);
        }
    }

    @Override
    public void onItemClick(View v, int clickedItemIndex) {
        Intent intent = new Intent(CitySearchResultsActivity.this, MainCityActivity.class);
        intent.putExtra(CITY_ID, resultCity.get(clickedItemIndex).getId());
        intent.putExtra(CITY_NAME, resultCity.get(clickedItemIndex).getName());
        intent.putParcelableArrayListExtra(CITY_IMAGE, (ArrayList<? extends Parcelable>) resultCity.get(clickedItemIndex).getImages());
        intent.putExtra(CITY_SNIPPET, resultCity.get(clickedItemIndex).getSnippet());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade(Fade.IN));
            getWindow().setExitTransition(new Fade(Fade.OUT));

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    new Pair<View, String>(v.findViewById(R.id.iv_city), getString(R.string.image_transition)),
                    new Pair<View, String>(v.findViewById(R.id.tv_city_name), getString(R.string.name_transition)),
                    new Pair<View, String>(v.findViewById(R.id.tv_city_snippet), getString(R.string.name_transition)));
            ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
