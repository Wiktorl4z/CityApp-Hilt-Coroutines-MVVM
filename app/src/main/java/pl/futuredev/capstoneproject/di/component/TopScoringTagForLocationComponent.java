package pl.futuredev.capstoneproject.di.component;

import dagger.Component;
import pl.futuredev.capstoneproject.di.module.TopScoringTagForLocationModule;
import pl.futuredev.capstoneproject.di.scope.TopPlacesToSeeScope;
import pl.futuredev.capstoneproject.di.scope.TopScoringTagForLocationScope;
import pl.futuredev.capstoneproject.ui.activities.topLocation.TopScoringTagForLocationFragment;

@TopScoringTagForLocationScope
@Component(modules = TopScoringTagForLocationModule.class, dependencies = ApplicationComponent.class)
public interface TopScoringTagForLocationComponent {

    void inject(TopScoringTagForLocationFragment fragment);

}

