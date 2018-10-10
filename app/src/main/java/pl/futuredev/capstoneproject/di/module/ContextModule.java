package pl.futuredev.capstoneproject.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.futuredev.capstoneproject.di.ApplicationContext;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    @ApplicationContext
    public Context context() {
        return context;
    }
}
