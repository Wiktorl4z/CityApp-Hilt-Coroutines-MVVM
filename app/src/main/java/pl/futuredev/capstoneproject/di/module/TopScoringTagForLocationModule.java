package pl.futuredev.capstoneproject.di.module;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.di.scope.FavCityFragmentScope;
import pl.futuredev.capstoneproject.di.scope.TopScoringTagForLocationScope;
import pl.futuredev.capstoneproject.ui.activities.favCity.FavCityActivity;
import pl.futuredev.capstoneproject.ui.activities.topLocation.TopScoringTagForLocationActivity;

@Module
public class TopScoringTagForLocationModule {

    private final TopScoringTagForLocationActivity activity;

    public TopScoringTagForLocationModule(TopScoringTagForLocationActivity activity) {
        this.activity = activity;
    }

    @Provides
    @TopScoringTagForLocationScope
    public TopScoringTagForLocationActivity activity() {
        return activity;
    }
}
