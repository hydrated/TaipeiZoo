package com.example.taipeizoo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.taipeizoo.MainApplication;
import com.example.taipeizoo.R;
import com.example.taipeizoo.fragment.PlantListFragment;
import com.example.taipeizoo.fragment.ZooFieldDetailFragment;
import com.example.taipeizoo.model.Plant;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.ui.PlantListView;
import com.example.taipeizoo.viewmodel.PlantViewModel;
import com.example.taipeizoo.viewmodel.ZooFieldViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZooFieldDetailActivity extends AppCompatActivity implements PlantListView.PlantListViewListener {

    public static final String FLAG_EXTRA_ZOO_FIELD_ID = "EXTRA_ZOO_FIELD";

    public static void start(Context context, ZooField zooField) {
        Intent intent = new Intent(context, ZooFieldDetailActivity.class);
        intent.putExtra(FLAG_EXTRA_ZOO_FIELD_ID, zooField._id);
        context.startActivity(intent);
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private PlantViewModel plantViewModel;
    private ZooFieldViewModel zooFieldViewModel;
    private PlantListFragment plantListFragment;
    private ZooFieldDetailFragment zooFieldDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ((MainApplication) getApplication()).getComponent().inject(this);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        zooFieldViewModel = ViewModelProviders.of(this, viewModelFactory).get(ZooFieldViewModel.class);
        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel.class);

        // TODO: nested object observation.
        zooFieldViewModel.getZooFields().observe(this, listResource -> {
            ZooField selectedZooField = null;
            if (listResource == null || listResource.data == null) return;
            for (ZooField zooField : listResource.data) {
                if (zooField._id == getIntent().getIntExtra(FLAG_EXTRA_ZOO_FIELD_ID, -1)) {
                    selectedZooField = zooField;
                }
            }
            if (selectedZooField == null) return;
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(selectedZooField.E_Name);
            }
            zooFieldDetailFragment.setZooDetail(selectedZooField);
            plantViewModel.getPlantsByArea(selectedZooField.E_Name).observe(this, listResource1 -> {
                plantListFragment.setPlants(listResource1);
            });
        });

    }

    private void init() {
        plantListFragment = PlantListFragment.getFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_view, plantListFragment);
        transaction.commit();
        zooFieldDetailFragment = ZooFieldDetailFragment.getFragment();
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.replace(R.id.detail_container, zooFieldDetailFragment);
        transaction1.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onPlantClicked(Plant plant) {
        PlantDetailActivity.start(this, plant);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }
}
