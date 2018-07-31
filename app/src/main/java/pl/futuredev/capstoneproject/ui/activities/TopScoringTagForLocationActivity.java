package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Recipe;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.APIService;
import pl.futuredev.capstoneproject.service.HttpConnector;
import pl.futuredev.capstoneproject.service.InternetReceiver;
import pl.futuredev.capstoneproject.ui.adapters.TopScoringTagForLocationAdapter;
import pl.futuredev.capstoneproject.ui.interfaces.IOnClickHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopScoringTagForLocationActivity extends AppCompatActivity implements IOnClickHandler {

    private static final String TAG = "TopPlacesToSeeActivity";
    private static final String CITY_ID = "city_id";

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    private InternetReceiver internetReceiver;
    private APIService service;
    private List<Result> resultList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String cityName;
    private Recipe recipe;
    private String cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scoring_tag_for_location);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        cityId = intent.getStringExtra(CITY_ID);

        internetReceiver = new InternetReceiver();
        service = HttpConnector.getService(APIService.class);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        getTopScoredTagsForLocation(cityId);
    }

    private void getTopScoredTagsForLocation(String cityId) {
        Log.e(TAG, service.getTopScoredTagsForLocation(cityId).request().url().toString());
        service.getTopScoredTagsForLocation(cityId).enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                settingUpView(response);
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Toast.makeText(TopScoringTagForLocationActivity.this, t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    ;

    private void settingUpView(Response<Recipe> response) {
        if (response.isSuccessful()) {
            resultList = response.body().getResults();
            adapter = new TopScoringTagForLocationAdapter(resultList, TopScoringTagForLocationActivity.this::onClick);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setLayoutManager(linearLayoutManager);
            ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
            scaleInAnimationAdapter.setDuration(350);
            scaleInAnimationAdapter.setFirstOnly(false);
            myRecyclerView.setAdapter(scaleInAnimationAdapter);

        } else {
            try {
                Toast.makeText(TopScoringTagForLocationActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT)
                        .show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(int clickedItemIndex) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) resultList.get(clickedItemIndex).getVendorTourUrl()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
