package pl.futuredev.capstoneproject.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.CapstoneApplication;

@Module
public class ApplicationModule {
    private final CapstoneApplication application;

    public ApplicationModule(CapstoneApplication application) {
        this.application = application;
    }

    @Provides
    CapstoneApplication provideCapstoneApplication() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

}
