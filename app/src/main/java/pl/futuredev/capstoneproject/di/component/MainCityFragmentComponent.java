package pl.futuredev.capstoneproject.di.component;

import android.app.Application;

import dagger.Component;
import pl.futuredev.capstoneproject.di.module.MainCityFragmentModule;
import pl.futuredev.capstoneproject.di.scope.MainCityFragmentScope;
import pl.futuredev.capstoneproject.ui.activities.mainCity.MainCityFragment;

@MainCityFragmentScope
@Component(modules = MainCityFragmentModule.class, dependencies = ApplicationComponent.class)
public interface MainCityFragmentComponent {

    void inject(MainCityFragment fragment);

}

