package com.example.taipeizoo.dagger;

import android.app.Application;

import androidx.room.Room;

import com.example.taipeizoo.db.PlantDao;
import com.example.taipeizoo.db.ZooDatabase;
import com.example.taipeizoo.db.ZooFieldDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    private Application mApplication;

    public DbModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    ZooDatabase provideZooDatabase() {
        return Room
                .databaseBuilder(mApplication, ZooDatabase.class, "zoo.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    ZooFieldDao provideZooFieldDao(ZooDatabase zooDatabase) {
        return zooDatabase.getZooFieldDao();
    }

    @Singleton
    @Provides
    PlantDao providePlantDao(ZooDatabase zooDatabase) {
        return zooDatabase.getPlantDao();
    }

}
