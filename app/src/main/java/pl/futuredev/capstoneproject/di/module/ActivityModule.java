package pl.futuredev.capstoneproject.di.module;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity context;

    public ActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @Singleton
    @Named("activity_context")
    public Context context() {
        return context;
    }
}
