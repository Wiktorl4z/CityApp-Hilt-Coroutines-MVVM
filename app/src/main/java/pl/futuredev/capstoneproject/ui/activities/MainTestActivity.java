package pl.futuredev.capstoneproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.futuredev.capstoneproject.R;

public class MainTestActivity extends AppCompatActivity {

    @BindView(R.id.bt_test_top_places_to_see)
    Button btTestTopPlacesToSee;
    @BindView(R.id.bt_test_top_places_to_eat)
    Button btTestTopPlacesToEat;
    @BindView(R.id.bt_test_top_scoring_tags)
    Button btTestTopScoringTags;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        city = intent.getStringExtra("city");

        btTestTopPlacesToSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainTestActivity.this, TopPlacesToSeeActivity.class);
                startActivity(intent);
            }
        });

        btTestTopPlacesToEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainTestActivity.this, TopPlacesToEatActivity.class);
                startActivity(intent);
            }
        });

        btTestTopScoringTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainTestActivity.this, TopScoringTagForLocationActivity.class);
                startActivity(intent);
            }
        });

    }
}
