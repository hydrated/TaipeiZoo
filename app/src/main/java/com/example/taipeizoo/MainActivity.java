package com.example.taipeizoo;

import android.arch.persistence.room.Insert;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.taipeizoo.db.ZooDatabase;
import com.example.taipeizoo.db.ZooFieldDao;
import com.example.taipeizoo.model.Response;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.service.request.ZooApi;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {


    @Inject
    ZooDatabase zooDatabase;
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
                final ZooField[] zooFields = response.body().result.results.toArray(new ZooField[0]);
                final ZooFieldDao dao = zooDatabase.getZooFieldDao();
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        dao.insert(zooFields);
                        ZooField[] fields = dao.getAllZooFields();
                        Log.d("hydrated", "");
                    }
                }).subscribeOn(Schedulers.io()).subscribe();

                Log.d("hydrated", "");
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("hydrated", "");
            }
        });
    }
}
