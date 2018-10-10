package pl.futuredev.capstoneproject;

import android.app.Activity;
import android.app.Application;

import com.squareup.picasso.Picasso;

import pl.futuredev.capstoneproject.di.component.ApplicationComponent;
import pl.futuredev.capstoneproject.di.component.DaggerApplicationComponent;
import pl.futuredev.capstoneproject.di.module.ApplicationModule;
import pl.futuredev.capstoneproject.di.module.ContextModule;
import pl.futuredev.capstoneproject.di.module.RoomModule;
import pl.futuredev.capstoneproject.service.TriposoService;
import timber.log.Timber;

public class CapstoneApplication extends Application {

    private ApplicationComponent applicationComponent;

    private TriposoService triposoService;
    private Picasso picasso;

    public static CapstoneApplication get(Activity activity) {
        return (CapstoneApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .contextModule(new ContextModule(this))
                .build();

        triposoService = applicationComponent.getTriposoService();
        picasso = applicationComponent.getPicasso();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
