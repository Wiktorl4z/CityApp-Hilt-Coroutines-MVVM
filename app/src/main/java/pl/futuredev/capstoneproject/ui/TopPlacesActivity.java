package pl.futuredev.capstoneproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.APIService;
import pl.futuredev.capstoneproject.service.HttpConnector;
import pl.futuredev.capstoneproject.service.InternetReceiver;
import pl.futuredev.capstoneproject.service.utils.UrlManager;
import pl.futuredev.capstoneproject.ui.adapters.TopPlacesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopPlacesActivity extends AppCompatActivity implements IOnClickHandler {

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    private InternetReceiver internetReceiver;
    private APIService service;
    private List<Result> result;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_places);
        ButterKnife.bind(this);


        if (UrlManager.API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.api_key_message, Toast.LENGTH_LONG).show();
        }

        internetReceiver = new InternetReceiver();
        service = HttpConnector.getService(APIService.class);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        getTopPlaces();

    }

    private void getTopPlaces() {
        service.getTopPlaces().enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                settingUpView(response);
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Toast.makeText(TopPlacesActivity.this, t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    ;

    private void settingUpView(Response<List<Result>> response) {
        if (response.isSuccessful()) {
            result = response.body();
            adapter = new TopPlacesAdapter(result, TopPlacesActivity.this::onClick);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setLayoutManager(linearLayoutManager);
            ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
            scaleInAnimationAdapter.setDuration(350);
            scaleInAnimationAdapter.setFirstOnly(false);
            myRecyclerView.setAdapter(scaleInAnimationAdapter);

        } else {
            try {
                Toast.makeText(TopPlacesActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT)
                        .show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(int clickedItemIndex) {
        Intent intent = new Intent(this, PlaceDetailActivity.class);
        intent.putExtra("result", result.get(clickedItemIndex));
        startActivity(intent);
    }

    ;
}
