package pl.futuredev.capstoneproject.di.module;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.di.scope.MainActivityScope;
import pl.futuredev.capstoneproject.ui.activities.main.MainActivity;
import pl.futuredev.capstoneproject.ui.activities.mainCity.MainCityActivity;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity mainActivity() {
        return mainActivity;
    }
}
