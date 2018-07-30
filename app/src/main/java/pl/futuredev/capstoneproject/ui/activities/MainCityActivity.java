package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Image;
import pl.futuredev.capstoneproject.models.Original;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.APIService;
import pl.futuredev.capstoneproject.service.HttpConnector;
import pl.futuredev.capstoneproject.service.InternetReceiver;

public class MainCityActivity extends AppCompatActivity {

    private static final String CITY_ID = "city_id";
    private static final String CITY_IMAGE = "city_image";
    private static final String CITY_SNIPPET = "city_snippet";
    private static final String CITY_NAME = "city_name";

    @BindView(R.id.bt_test_top_places_to_see)
    Button btTestTopPlacesToSee;
    @BindView(R.id.bt_test_top_places_to_eat)
    Button btTestTopPlacesToEat;
    @BindView(R.id.bt_test_top_scoring_tags)
    Button btTestTopScoringTags;
    @BindView(R.id.ThreeTwoImage)
    ThreeTwoImageView threeTwoImage;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tv_city_main_snippet)
    TextView tvCityMainSnippet;
    @BindView(R.id.tv_city_main_name)
    TextView tvCityMainName;

    private Image image;
    private InternetReceiver internetReceiver;
    private APIService service;
    private List<Result> resultList;
    private List<Image> imageList;
    private String cityId;
    private String citySnippet;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_main);
        ButterKnife.bind(this);

        internetReceiver = new InternetReceiver();
        service = HttpConnector.getService(APIService.class);

        Intent intent = getIntent();
        cityId = intent.getStringExtra(CITY_ID);
        cityName = intent.getStringExtra(CITY_NAME);
        citySnippet = getIntent().getStringExtra(CITY_SNIPPET);
        imageList = intent.getParcelableArrayListExtra(CITY_IMAGE);

        settingUpView();

        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();

        btTestTopPlacesToSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCityActivity.this, TopPlacesToSeeActivity.class);
                intent.putExtra(CITY_NAME, cityId);
                startActivity(intent);
            }
        });

        btTestTopPlacesToEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCityActivity.this, TopPlacesToEatActivity.class);
                intent.putExtra(CITY_NAME, cityId);
                startActivity(intent);
            }
        });

        btTestTopScoringTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCityActivity.this, TopScoringTagForLocationActivity.class);
                intent.putExtra(CITY_NAME, cityId);
                startActivity(intent);
            }
        });

    }


    private void settingUpView() {
        if (imageList != null && !imageList.isEmpty()) {
            Original originalImage = imageList.get(0).getSizes().getOriginal();
            Picasso.get()
                    .load(originalImage.getUrl())
                    .into(threeTwoImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.get().load(originalImage.getUrl()).into(threeTwoImage);

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(R.drawable.rest1).into(threeTwoImage);
                        }
                    });
        }
        tvCityMainSnippet.setText(citySnippet);
        tvCityMainName.setText(cityName);
    }
}
