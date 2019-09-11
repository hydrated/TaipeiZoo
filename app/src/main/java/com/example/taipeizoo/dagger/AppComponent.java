package com.example.taipeizoo.dagger;

import com.example.taipeizoo.activity.MainActivity;
import com.example.taipeizoo.MainApplication;
import com.example.taipeizoo.activity.PlantDetailActivity;
import com.example.taipeizoo.activity.ZooFieldDetailActivity;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {NetModule.class, AppModule.class, DbModule.class})
public interface AppComponent {

    void inject(MainApplication application);

    void inject(MainActivity activity);

    void inject(PlantDetailActivity activity);

    void inject(ZooFieldDetailActivity activity);

}
