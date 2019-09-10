package com.example.taipeizoo.dagger;

import com.example.taipeizoo.MainActivity;
import com.example.taipeizoo.MainApplication;
import com.example.taipeizoo.db.ZooDatabase;
import com.example.taipeizoo.db.ZooFieldDao;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {NetModule.class, AppModule.class, DbModule.class})
public interface AppComponent {

    void inject(MainApplication application);

    void inject(MainActivity activity);

}
