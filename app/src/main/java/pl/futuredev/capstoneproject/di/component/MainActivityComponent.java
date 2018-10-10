package pl.futuredev.capstoneproject.di.component;

import javax.inject.Singleton;

import dagger.Component;
import pl.futuredev.capstoneproject.di.module.MainActivityModule;
import pl.futuredev.capstoneproject.di.module.RoomModule;
import pl.futuredev.capstoneproject.di.scope.MainActivityScope;
import pl.futuredev.capstoneproject.ui.activities.main.MainActivity;

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
