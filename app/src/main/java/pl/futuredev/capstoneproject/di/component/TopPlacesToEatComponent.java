package pl.futuredev.capstoneproject.di.component;

import dagger.Component;
import pl.futuredev.capstoneproject.di.module.TopPlacesToEatModule;
import pl.futuredev.capstoneproject.di.module.TopScoringTagForLocationModule;
import pl.futuredev.capstoneproject.di.scope.TopPlacesToEatScope;
import pl.futuredev.capstoneproject.di.scope.TopScoringTagForLocationScope;
import pl.futuredev.capstoneproject.ui.activities.topLocation.TopScoringTagForLocationFragment;
import pl.futuredev.capstoneproject.ui.activities.topPlacesToEat.TopPlacesToEatFragment;

@TopPlacesToEatScope
@Component(modules = TopPlacesToEatModule.class, dependencies = ApplicationComponent.class)
public interface TopPlacesToEatComponent {

    void inject(TopPlacesToEatFragment fragment);

}

