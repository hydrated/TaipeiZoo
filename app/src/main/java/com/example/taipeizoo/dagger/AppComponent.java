package com.example.taipeizoo.dagger;

import com.example.taipeizoo.MainActivity;
import com.example.taipeizoo.MainApplication;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {NetModule.class, AppModule.class})
public interface AppComponent {

    void inject(MainApplication application);

    void inject(MainActivity activity);
}
