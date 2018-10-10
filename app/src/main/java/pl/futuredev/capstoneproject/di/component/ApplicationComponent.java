package pl.futuredev.capstoneproject.di.component;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import pl.futuredev.capstoneproject.di.module.ActivityModule;
import pl.futuredev.capstoneproject.di.module.ApplicationModule;
import pl.futuredev.capstoneproject.di.module.PicassoModule;
import pl.futuredev.capstoneproject.di.module.RoomModule;
import pl.futuredev.capstoneproject.di.module.TriposoServiceModule;
import pl.futuredev.capstoneproject.service.TriposoService;

@Singleton
@Component(modules = {TriposoServiceModule.class, ApplicationModule.class, RoomModule.class, PicassoModule.class, ActivityModule.class})
public interface ApplicationComponent {

    Picasso getPicasso();

    TriposoService getTriposoService();

    Application application();

    ViewModelProvider.Factory provideViewModelFactory();

}
