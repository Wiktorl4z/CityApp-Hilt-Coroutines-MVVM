package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import pl.futuredev.capstoneproject.service.utils.UrlManager;
import pl.futuredev.capstoneproject.ui.adapters.TopPlacesToEatAdapter;
import pl.futuredev.capstoneproject.ui.adapters.TopPlacesToSeeAdapter;
import pl.futuredev.capstoneproject.ui.interfaces.IOnClickHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopPlacesToEatActivity extends AppCompatActivity {

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    private InternetReceiver internetReceiver;
    private APIService service;
    private List<Result> resultList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String cityName;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_places_to_eat);
        ButterKnife.bind(this);
        if (UrlManager.API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.api_key_message, Toast.LENGTH_LONG).show();
        }
        internetReceiver = new InternetReceiver();
        service = HttpConnector.getService(APIService.class);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        getTopPlacesToEat();
    }

    private void getTopPlacesToEat() {
        service.getTopPlacesToEat().enqueue(new Callback<Recipe>() {
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
            adapter = new TopPlacesToEatAdapter(resultList);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setLayoutManager(linearLayoutManager);
            ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
            scaleInAnimationAdapter.setDuration(350);
            scaleInAnimationAdapter.setFirstOnly(false);
            myRecyclerView.setAdapter(scaleInAnimationAdapter);
        } else {
            try {
                Toast.makeText(TopPlacesToEatActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT)
                        .show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}



