package com.example.taipeizoo.dagger;

import android.app.Application;
import android.util.Log;

import com.example.taipeizoo.service.request.ZooApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private final String TAG = NetModule.class.getSimpleName();
    private final Application application;
    private String mBaseUrl;

    public NetModule(Application application, String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
        this.application = application;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient httpClient) {

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(httpClient)
                .build();
    }

    @Provides
    @Singleton
    ZooApi provideZooApi(Retrofit retrofit) {
        return retrofit.create(ZooApi.class);
    }

}
