package pl.futuredev.capstoneproject.di.module;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.di.scope.FavCityFragmentScope;
import pl.futuredev.capstoneproject.ui.activities.favCity.FavCityActivity;

@Module
public class FavCityFragmentModule {

    private final FavCityActivity activity;

    public FavCityFragmentModule(FavCityActivity activity) {
        this.activity = activity;
    }

    @Provides
    @FavCityFragmentScope
    public FavCityActivity activity() {
        return activity;
    }
}
