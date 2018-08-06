package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import pl.futuredev.capstoneproject.ui.adapters.TopPlacesToEatAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopPlacesToEatActivity extends AppCompatActivity {

    private static final String TAG = "TopPlacesToSeeActivity";
    private static final String CITY_ID = "city_id";

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @BindView(R.id.iv_no_city)
    ImageView ivNoCity;
    @BindView(R.id.tv_no_found_city)
    TextView tvNoFoundCity;
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
        setContentView(R.layout.activity_top_places_to_eat);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        cityId = intent.getStringExtra(CITY_ID);

        internetReceiver = new InternetReceiver();
        service = HttpConnector.getService(APIService.class);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        getTopPlacesToEat(cityId);
    }

    private void getTopPlacesToEat(String cityId) {
        Log.e(TAG, service.getTopPlacesToEat(cityId).request().url().toString());
        service.getTopPlacesToEat(cityId).enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                settingUpView(response);
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Toast.makeText(TopPlacesToEatActivity.this, t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    ;

    private void settingUpView(Response<Recipe> response) {
        if (response.isSuccessful()) {
            resultList = response.body().getResults();
            if (resultList.isEmpty()) {
                myRecyclerView.setVisibility(View.INVISIBLE);
                ivNoCity.setVisibility(View.VISIBLE);
                tvNoFoundCity.setVisibility(View.VISIBLE);
            } else {
                myRecyclerView.setVisibility(View.VISIBLE);
                ivNoCity.setVisibility(View.INVISIBLE);
                tvNoFoundCity.setVisibility(View.INVISIBLE);
                adapter = new TopPlacesToEatAdapter(resultList);
                myRecyclerView.setHasFixedSize(true);
                myRecyclerView.setLayoutManager(linearLayoutManager);
                ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
                scaleInAnimationAdapter.setDuration(350);
                scaleInAnimationAdapter.setFirstOnly(false);
                myRecyclerView.setAdapter(scaleInAnimationAdapter);
            }
        }
    }
}



