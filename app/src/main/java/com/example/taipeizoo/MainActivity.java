package com.example.taipeizoo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.taipeizoo.db.ZooDatabase;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.service.Resource;
import com.example.taipeizoo.service.request.ZooApi;
import com.example.taipeizoo.viewmodel.ZooFieldViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ZooFieldViewModel zooFieldViewModel;
    @Inject
    ZooDatabase zooDatabase;
    @Inject
    ZooApi zooApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getComponent().inject(this);

        zooFieldViewModel = ViewModelProviders.of(this, viewModelFactory).get(ZooFieldViewModel.class);
        zooFieldViewModel.getZooFields().observe(this, new Observer<Resource<List<ZooField>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<ZooField>> listResource) {
                Log.d("hydrated", ""+listResource);
            }
        });
    }
}
