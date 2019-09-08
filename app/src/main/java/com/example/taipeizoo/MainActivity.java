package com.example.taipeizoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.taipeizoo.model.Response;
import com.example.taipeizoo.service.request.ZooApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    @Inject
    ZooApi zooApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getComponent().inject(this);

        zooApi.searchReposRX("").enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d("hydrated", "");
                Response response1 = response.body();

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("hydrated", "");
            }
        });
    }
}
