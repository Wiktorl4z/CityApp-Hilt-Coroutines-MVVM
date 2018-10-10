package pl.futuredev.capstoneproject.ui.activities.citySearchResults;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.ui.activities.mainCity.MainCityActivity;
import pl.futuredev.capstoneproject.ui.adapters.CityResultAdapter;

public class CitySearchResultFragment extends Fragment implements CityResultAdapter.ItemClickCallback {

    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE = "city_image";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";
    private CityResultAdapter cityResultAdapter;
    private List<Result> resultCity;
    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.my_recycler_view_fragment)
    RecyclerView myRecyclerView;
    @BindView(R.id.iv_no_city)
    ImageView ivNoCity;
    @BindView(R.id.tv_no_found_city)
    TextView tvNoFoundCity;
    Unbinder unbinder;

    public CitySearchResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public static CitySearchResultFragment newInstance(List<Result> resultCity) {
        CitySearchResultFragment citySearchResultFragment = new CitySearchResultFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("resultCity", (ArrayList<? extends Parcelable>) resultCity);
        citySearchResultFragment.setArguments(args);
        return citySearchResultFragment;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_search_result, container, false);
        unbinder = ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        resultCity = getArguments().getParcelableArrayList("resultCity");
        if (resultCity.isEmpty()) {
            myRecyclerView.setVisibility(View.INVISIBLE);
            tvNoFoundCity.setVisibility(View.VISIBLE);
            ivNoCity.setVisibility(View.VISIBLE);
        } else {
            myRecyclerView.setVisibility(View.VISIBLE);
            tvNoFoundCity.setVisibility(View.INVISIBLE);
            ivNoCity.setVisibility(View.INVISIBLE);
            cityResultAdapter = new CityResultAdapter(resultCity, CitySearchResultFragment.this::onItemClick);
            myRecyclerView.setHasFixedSize(true);
            myRecyclerView.setLayoutManager(linearLayoutManager);
            myRecyclerView.setAdapter(cityResultAdapter);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(View v, int clickedItemIndex) {
        Intent intent = new Intent(getContext(), MainCityActivity.class);
        intent.putExtra(CITY_ID, resultCity.get(clickedItemIndex).getId());
        intent.putExtra(CITY_NAME, resultCity.get(clickedItemIndex).getName());
        intent.putParcelableArrayListExtra(CITY_IMAGE, (ArrayList<? extends Parcelable>) resultCity.get(clickedItemIndex).getImages());
        intent.putExtra(CITY_SNIPPET, resultCity.get(clickedItemIndex).getSnippet());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setEnterTransition(new Fade(Fade.IN));
            getActivity().getWindow().setExitTransition(new Fade(Fade.OUT));

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(),
                    new Pair<View, String>(v.findViewById(R.id.iv_city), getString(R.string.image_transition)),
                    new Pair<View, String>(v.findViewById(R.id.tv_city_name), getString(R.string.name_transition)),
                    new Pair<View, String>(v.findViewById(R.id.tv_city_snippet), getString(R.string.name_transition)));
            ActivityCompat.startActivity(getContext(), intent, optionsCompat.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
