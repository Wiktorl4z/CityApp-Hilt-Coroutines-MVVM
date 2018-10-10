package pl.futuredev.capstoneproject.di.module;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.di.scope.MainActivityScope;
import pl.futuredev.capstoneproject.di.scope.MainCityFragmentScope;
import pl.futuredev.capstoneproject.ui.activities.main.MainActivity;
import pl.futuredev.capstoneproject.ui.activities.mainCity.MainCityActivity;
import pl.futuredev.capstoneproject.ui.activities.mainCity.MainCityFragment;

@Module
public class MainCityFragmentModule {

    private final MainCityActivity activity;

    public MainCityFragmentModule(MainCityActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MainCityFragmentScope
    public MainCityActivity activity() {
        return activity;
    }
}
