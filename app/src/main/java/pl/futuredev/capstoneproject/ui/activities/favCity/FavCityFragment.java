package pl.futuredev.capstoneproject.ui.activities.favCity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import pl.futuredev.capstoneproject.CapstoneApplication;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;
import pl.futuredev.capstoneproject.di.component.DaggerFavCityFragmentComponent;
import pl.futuredev.capstoneproject.di.module.FavCityFragmentModule;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.InternetReceiver;
import pl.futuredev.capstoneproject.ui.activities.mainCity.MainCityActivity;
import pl.futuredev.capstoneproject.ui.adapters.FavouritesCityAdapter;
import pl.futuredev.capstoneproject.ui.interfaces.IOnClickHandler;
import pl.futuredev.capstoneproject.viewmodel.CityCollectionViewModel;

public class FavCityFragment extends Fragment implements IOnClickHandler {

    private static final String TAG = FavCityFragment.class.getSimpleName();
    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE_STRING = "city_image_string";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";
    private static final String CITY_IMAGE = "city_image";
    private FavouritesCityAdapter cityResultAdapter;
    private List<Result> resultCity;
    private LinearLayoutManager linearLayoutManager;
    private InternetReceiver internetReceiver;
    private List<CityPOJO> cities;
    private boolean isFavourite;
    private final CompositeDisposable disposables = new CompositeDisposable();
    Unbinder unbinder;

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @BindView(R.id.tv_no_fav_text)
    TextView tvNoFavText;
    @BindView(R.id.iv_no_city_fav)
    ImageView ivNoCityFav;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CityCollectionViewModel cityCollectionViewModel;

    public FavCityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        DaggerFavCityFragmentComponent.builder()
                .favCityFragmentModule(new FavCityFragmentModule(((FavCityActivity) getContext())))
                .applicationComponent(CapstoneApplication.get(getActivity()).getApplicationComponent())
                .build().inject(this);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cityCollectionViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CityCollectionViewModel.class);

        checkFavouritesCitiesInDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_city, container, false);
        unbinder = ButterKnife.bind(this, view);

        ivNoCityFav.setVisibility(View.INVISIBLE);
        tvNoFavText.setVisibility(View.INVISIBLE);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showToast(final String toast) {
        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show());
    }

    private void settingUpView(List<CityPOJO> cities) {
        if (isFavourite) {
            ivNoCityFav.setVisibility(View.INVISIBLE);
            tvNoFavText.setVisibility(View.INVISIBLE);
            cityResultAdapter = new FavouritesCityAdapter(cities, cityCollectionViewModel, FavCityFragment.this);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setLayoutManager(linearLayoutManager);
            myRecyclerView.setAdapter(cityResultAdapter);
        } else {
            myRecyclerView.setVisibility(View.INVISIBLE);
            ivNoCityFav.setVisibility(View.VISIBLE);
            tvNoFavText.setVisibility(View.VISIBLE);
            showToast(getString(R.string.no_cities_in_fav));
        }
    }

    @Override
    public void onClick(int clickedItemIndex) {
        Intent intent = new Intent(getContext(), MainCityActivity.class);
        intent.putExtra(CITY_ID, cities.get(clickedItemIndex).getCityId());
        intent.putExtra(CITY_NAME, cities.get(clickedItemIndex).getName());
        intent.putExtra(CITY_SNIPPET, cities.get(clickedItemIndex).getSnippet());
        intent.putParcelableArrayListExtra(CITY_IMAGE, (ArrayList<? extends Parcelable>) cities.get(clickedItemIndex).getImages());
        startActivity(intent);
    }

    public void checkFavouritesCitiesInDatabase() {
        disposables.add(cityCollectionViewModel.getAllCitiesRx()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(List<CityPOJO> cityPOJOS) {
        if (!cityPOJOS.isEmpty()) {
            cities = cityPOJOS;
            isFavourite = true;
            settingUpView(cityPOJOS);
        } else {
            isFavourite = false;
            settingUpView(cityPOJOS);
        }
    }


    private void handleError(Throwable throwable) {
        Log.d(TAG, throwable.getMessage());
        Toast.makeText(getContext(), "Error accessing database" + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        disposables.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkFavouritesCitiesInDatabase();
    }
}
