package pl.futuredev.capstoneproject.di.module;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.di.scope.TopPlacesToSeeScope;
import pl.futuredev.capstoneproject.di.scope.TopScoringTagForLocationScope;
import pl.futuredev.capstoneproject.ui.activities.topLocation.TopScoringTagForLocationActivity;
import pl.futuredev.capstoneproject.ui.activities.topPlacesToSee.TopPlacesToSeeActivity;

@Module
public class TopPlacesToSeeModule {

    private final TopPlacesToSeeActivity activity;

    public TopPlacesToSeeModule(TopPlacesToSeeActivity activity) {
        this.activity = activity;
    }

    @Provides
    @TopPlacesToSeeScope
    public TopPlacesToSeeActivity activity() {
        return activity;
    }
}
