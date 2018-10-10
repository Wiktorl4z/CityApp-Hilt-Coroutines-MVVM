package pl.futuredev.capstoneproject.ui.activities.topLocation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import pl.futuredev.capstoneproject.CapstoneApplication;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.di.component.DaggerTopScoringTagForLocationComponent;
import pl.futuredev.capstoneproject.di.module.TopScoringTagForLocationModule;
import pl.futuredev.capstoneproject.models.Image;
import pl.futuredev.capstoneproject.models.Recipe;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.InternetReceiver;
import pl.futuredev.capstoneproject.service.TriposoService;
import pl.futuredev.capstoneproject.ui.adapters.TopScoringTagForLocationAdapter;
import pl.futuredev.capstoneproject.ui.interfaces.IOnClickHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopScoringTagForLocationFragment extends Fragment implements IOnClickHandler {

    private static final String TAG = TopScoringTagForLocationFragment.class.getSimpleName();
    private static final String CITY_ID = "city_id";

    private InternetReceiver internetReceiver;
    private TriposoService service;
    private List<Result> resultList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String cityName;
    private Recipe recipe;
    private String cityId;
    Unbinder unbinder;

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @BindView(R.id.iv_no_city)
    ImageView ivNoCity;
    @BindView(R.id.tv_no_found_city)
    TextView tvNoFoundCity;

    @Inject
    TriposoService triposoService;

    public TopScoringTagForLocationFragment() {
        // Required empty public constructor
    }

    public static TopScoringTagForLocationFragment newInstance(String cityId) {
        TopScoringTagForLocationFragment topScoringTagForLocationFragment = new TopScoringTagForLocationFragment();
        Bundle args = new Bundle();
        args.putString(CITY_ID, cityId);
        topScoringTagForLocationFragment.setArguments(args);
        return topScoringTagForLocationFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        DaggerTopScoringTagForLocationComponent.builder()
                .topScoringTagForLocationModule(new TopScoringTagForLocationModule(((TopScoringTagForLocationActivity) getContext())))
                .applicationComponent(CapstoneApplication.get(getActivity()).getApplicationComponent())
                .build().inject(this);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_scoring_tag_for_location, container, false);

        unbinder = ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            cityId = getArguments().getString(CITY_ID);
        }

        internetReceiver = new InternetReceiver();
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        getTopScoredTagsForLocation(cityId);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getTopScoredTagsForLocation(String cityId) {
        triposoService.getTopScoredTagsForLocation(cityId).enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                settingUpView(response);
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT)
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
                adapter = new TopScoringTagForLocationAdapter(resultList, TopScoringTagForLocationFragment.this);
                myRecyclerView.setHasFixedSize(true);
                myRecyclerView.setLayoutManager(linearLayoutManager);
                ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
                scaleInAnimationAdapter.setDuration(350);
                scaleInAnimationAdapter.setFirstOnly(false);
                myRecyclerView.setAdapter(scaleInAnimationAdapter);
            }
        }
    }

    @Override
    public void onClick(int clickedItemIndex) {
        List<Image> images = resultList.get(clickedItemIndex).getImages();
        if (images.get(0).getOwnerUrl().isEmpty()) {
            Toast.makeText(getContext(), R.string.link_donesnt_work, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(images.get(0).getOwnerUrl()));
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}
