package pl.futuredev.capstoneproject.ui.activities.mainCity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import pl.futuredev.capstoneproject.CapstoneApplication;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.database.entity.CityPOJO;
import pl.futuredev.capstoneproject.database.repository.CityItemRepository;
import pl.futuredev.capstoneproject.di.component.DaggerMainCityFragmentComponent;
import pl.futuredev.capstoneproject.di.module.MainCityFragmentModule;
import pl.futuredev.capstoneproject.models.Image;
import pl.futuredev.capstoneproject.models.Original;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.ui.activities.ThreeTwoImageView;
import pl.futuredev.capstoneproject.ui.activities.topLocation.TopScoringTagForLocationActivity;
import pl.futuredev.capstoneproject.ui.activities.topPlacesToEat.TopPlacesToEatActivity;
import pl.futuredev.capstoneproject.ui.activities.topPlacesToSee.TopPlacesToSeeActivity;
import pl.futuredev.capstoneproject.viewmodel.CityCollectionViewModel;

public class MainCityFragment extends Fragment {

    private static final String TAG = MainCityFragment.class.getSimpleName();
    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE = "city_image";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";
    private static final String CITY_IMAGE_STRING = "city_image_string";
    private Image image;
    private List<Result> resultList;
    private List<Image> imageList;
    private String cityId;
    private String citySnippet;
    private String cityName;
    private CityPOJO cityPOJO;
    private boolean isFavourite;
    private Original originalImage;
    private String originalImageUrl;
    private CityItemRepository cityItemRepository;
    private Toast toast;
    Unbinder unbinder;
    private CityCollectionViewModel cityCollectionViewModel;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @BindView(R.id.threeTwoImage)
    ThreeTwoImageView threeTwoImage;
    @BindView(R.id.action_up)
    ImageButton actionUp;
    @BindView(R.id.toolbarId)
    Toolbar toolbarId;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.bt_test_top_places_to_see)
    Button btTestTopPlacesToSee;
    @BindView(R.id.bt_test_top_places_to_eat)
    Button btTestTopPlacesToEat;
    @BindView(R.id.bt_test_top_scoring_tags)
    Button btTestTopScoringTags;
    @BindView(R.id.tv_city_main_snippet)
    TextView tvCityMainSnippet;
    @BindView(R.id.tv_city_main_name)
    TextView tvCityMainName;
    @BindView(R.id.tv_best_places)
    TextView tvBestPlaces;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public MainCityFragment() {
    }

    public static MainCityFragment newInstance(String cityId, String cityName, String citySnippet, List<Image> imageList) {
        MainCityFragment mainCityFragment = new MainCityFragment();
        Bundle args = new Bundle();
        args.putString(CITY_ID, cityId);
        args.putString(CITY_NAME, cityName);
        args.putString(CITY_SNIPPET, citySnippet);
        args.putParcelableArrayList(CITY_IMAGE, (ArrayList<? extends Parcelable>) imageList);
        mainCityFragment.setArguments(args);
        return mainCityFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        DaggerMainCityFragmentComponent.builder()
                .mainCityFragmentModule(new MainCityFragmentModule(((MainCityActivity) getContext())))
                .applicationComponent(CapstoneApplication.get(getActivity()).getApplicationComponent())
                .build().inject(this);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cityCollectionViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CityCollectionViewModel.class);

        checkCityIfIsInDatabaseRxJava();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_city, container, false);
        unbinder = ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            cityId = getArguments().getString(CITY_ID);
            citySnippet = getArguments().getString(CITY_SNIPPET);
            cityName = getArguments().getString(CITY_NAME);
            imageList = getArguments().getParcelableArrayList(CITY_IMAGE);
        }

        if (imageList != null && !imageList.isEmpty()) {
            originalImage = imageList.get(0).getSizes().getOriginal();
            originalImageUrl = originalImage.getUrl();
        } else {
            originalImageUrl = "";
        }

        settingUpView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrRemoveToDatabase(cityPOJO);
                colorSwitcherForFAB();
            }
        });

        btTestTopPlacesToSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TopPlacesToSeeActivity.class);
                intent.putExtra(CITY_ID, cityId);
                startActivity(intent);
            }
        });

        btTestTopPlacesToEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TopPlacesToEatActivity.class);
                intent.putExtra(CITY_ID, cityId);
                startActivity(intent);
            }
        });

        btTestTopScoringTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TopScoringTagForLocationActivity.class);
                intent.putExtra(CITY_ID, cityId);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void checkCityIfIsInDatabaseRxJava() {
        disposables.add(cityCollectionViewModel.getSelectedCityFromDatabase(cityName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(CityPOJO city) {
        if (city != null) {
            cityPOJO = city;
            isFavourite = true;
        } else {
            isFavourite = false;
        }
        colorSwitcherForFAB();
    }

    private void handleError(Throwable throwable) {
        Log.d(TAG, throwable.getMessage());
        Toast.makeText(getContext(), "Error accessing database" + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    public void addOrRemoveToDatabase(CityPOJO cityPOJO) {
        if (isFavourite) {
            removeFromDatabase(cityPOJO);
            isFavourite = false;
            showToast(getString(R.string.city_removed_from_db));
        } else {
            addToFavourite();
            isFavourite = true;
            showToast(getString(R.string.add_city_to_db));
        }
    }

    private void addToFavourite() {
        cityPOJO = new CityPOJO(cityName, citySnippet, imageList, cityId);
        disposables.add(Completable.fromAction(() -> cityCollectionViewModel.addNewCityToDatabase(cityPOJO))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe());
    }

    private void removeFromDatabase(CityPOJO cityPOJO) {
        disposables.add(Completable.fromAction(() -> cityCollectionViewModel.deleteCityFromList(cityPOJO))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe());
    }

    private void colorSwitcherForFAB() {
        if (isFavourite) {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#087f23")));
        } else {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color
                    .parseColor("#c2c1c1")));
        }
    }

    private void settingUpView() {
        Picasso.get()
                .load(originalImageUrl)
                .into(threeTwoImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.get().load(originalImageUrl).into(threeTwoImage);
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(R.drawable.rest1).into(threeTwoImage);
                    }
                });

        tvCityMainSnippet.setText(citySnippet);
        tvCityMainName.setText(cityName);
        actionUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO fix it
                //onSupportNavigateUp();
            }
        });
    }

    public void showToast(final String message) {
        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show());
    }

    public void onPause() {
        if (toast != null)
            toast.cancel();
        super.onPause();
    }


    @Override
    public void onStop() {
        super.onStop();
        disposables.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkCityIfIsInDatabaseRxJava();
    }
}
