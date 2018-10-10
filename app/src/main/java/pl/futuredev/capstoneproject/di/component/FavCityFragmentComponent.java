package pl.futuredev.capstoneproject.di.component;

import dagger.Component;
import pl.futuredev.capstoneproject.di.module.FavCityFragmentModule;
import pl.futuredev.capstoneproject.di.module.MainCityFragmentModule;
import pl.futuredev.capstoneproject.di.scope.FavCityFragmentScope;
import pl.futuredev.capstoneproject.di.scope.MainCityFragmentScope;
import pl.futuredev.capstoneproject.ui.activities.favCity.FavCityActivity;
import pl.futuredev.capstoneproject.ui.activities.favCity.FavCityFragment;
import pl.futuredev.capstoneproject.ui.activities.mainCity.MainCityFragment;

@FavCityFragmentScope
@Component(modules = FavCityFragmentModule.class, dependencies = ApplicationComponent.class)
public interface FavCityFragmentComponent {

    void inject(FavCityFragment fragment);

}

