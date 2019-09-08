package com.example.taipeizoo;

import android.app.Application;

import com.example.taipeizoo.dagger.AppComponent;
import com.example.taipeizoo.dagger.AppModule;
import com.example.taipeizoo.dagger.DaggerAppComponent;
import com.example.taipeizoo.dagger.NetModule;
import com.example.taipeizoo.service.request.ZooApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainApplication extends Application {

    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(this, BuildConfig.URL))
                .build();
        mComponent.inject(this);

    }

    public AppComponent getComponent() {
        return mComponent;
    }
}
