package com.example.taipeizoo.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.taipeizoo.MainApplication;
import com.example.taipeizoo.R;
import com.example.taipeizoo.db.ZooDatabase;
import com.example.taipeizoo.model.Plant;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.service.Resource;
import com.example.taipeizoo.service.request.ZooApi;
import com.example.taipeizoo.ui.ZooFieldListView;
import com.example.taipeizoo.viewmodel.PlantViewModel;
import com.example.taipeizoo.viewmodel.ZooFieldViewModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ZooFieldListView.ZooFieldListViewListener {


    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ZooFieldViewModel zooFieldViewModel;
    private PlantViewModel plantViewModel;
    @Inject
    ZooDatabase zooDatabase;
    @Inject
    ZooApi zooApi;
    @BindView(R.id.zoo_field_list)
    ZooFieldListView zooFieldListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((MainApplication) getApplication()).getComponent().inject(this);

        zooFieldListView.setZooFieldListViewListener(this);
        zooFieldViewModel = ViewModelProviders.of(this, viewModelFactory).get(ZooFieldViewModel.class);
        zooFieldViewModel.getZooFields().observe(this, listResource -> {
            zooFieldListView.setZooFieldList(listResource.data);
        });
        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel.class);
        plantViewModel.getPlants().observe(this, listResource -> {
        });

    }

    @Override
    public void onZooFieldClicked(ZooField zooField) {
        ZooFieldDetailActivity.start(this, zooField);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }
}
