package pl.futuredev.capstoneproject.di.component;

import dagger.Component;
import pl.futuredev.capstoneproject.di.module.TopPlacesToSeeModule;
import pl.futuredev.capstoneproject.di.module.TopScoringTagForLocationModule;
import pl.futuredev.capstoneproject.di.scope.TopScoringTagForLocationScope;
import pl.futuredev.capstoneproject.ui.activities.topLocation.TopScoringTagForLocationFragment;
import pl.futuredev.capstoneproject.ui.activities.topPlacesToSee.TopPlacesToSeeFragment;

@TopScoringTagForLocationScope
@Component(modules = TopPlacesToSeeModule.class, dependencies = ApplicationComponent.class)
public interface TopPlacesToSeeComponent {

    void inject(TopPlacesToSeeFragment fragment);

}

