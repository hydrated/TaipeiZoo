package com.example.taipeizoo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.taipeizoo.db.ZooDatabase;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.service.Resource;
import com.example.taipeizoo.service.request.ZooApi;
import com.example.taipeizoo.ui.ZooFieldListView;
import com.example.taipeizoo.viewmodel.ZooFieldViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ZooFieldListView.ZooFieldListViewListener {


    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ZooFieldViewModel zooFieldViewModel;
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
        zooFieldListView.setZooFieldListViewListener(this);

        ((MainApplication) getApplication()).getComponent().inject(this);

        zooFieldViewModel = ViewModelProviders.of(this, viewModelFactory).get(ZooFieldViewModel.class);
        zooFieldViewModel.getZooFields().observe(this, listResource -> Log.d("hydrated", ""+listResource));
    }

    @Override
    public void onZooFieldClicked(ZooField zooField) {

    }
}
