package com.example.taipeizoo.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.taipeizoo.db.ZooDatabase;
import com.example.taipeizoo.db.ZooFieldDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

}