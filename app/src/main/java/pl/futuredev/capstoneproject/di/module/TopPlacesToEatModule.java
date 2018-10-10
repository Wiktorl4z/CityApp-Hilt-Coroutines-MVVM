package pl.futuredev.capstoneproject.di.module;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.di.scope.TopPlacesToEatScope;
import pl.futuredev.capstoneproject.di.scope.TopScoringTagForLocationScope;
import pl.futuredev.capstoneproject.ui.activities.topLocation.TopScoringTagForLocationActivity;
import pl.futuredev.capstoneproject.ui.activities.topPlacesToEat.TopPlacesToEatActivity;

@Module
public class TopPlacesToEatModule {

    private final TopPlacesToEatActivity activity;

    public TopPlacesToEatModule(TopPlacesToEatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @TopPlacesToEatScope
    public TopPlacesToEatActivity activity() {
        return activity;
    }
}
