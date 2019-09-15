package com.example.taipeizoo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.taipeizoo.MainApplication;
import com.example.taipeizoo.R;
import com.example.taipeizoo.fragment.PlantDetailFragment;
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

public class PlantDetailActivity extends AppCompatActivity {

    public static final String FLAG_EXTRA_PLANT_ID = "EXTRA_PLANT";

    public static void start(Context context, Plant plant) {
        Intent intent = new Intent(context, PlantDetailActivity.class);
        intent.putExtra(FLAG_EXTRA_PLANT_ID, plant._id);
        context.startActivity(intent);
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private PlantViewModel plantViewModel;
    private PlantDetailFragment plantDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);
        ButterKnife.bind(this);
        ((MainApplication) getApplication()).getComponent().inject(this);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel.class);

        plantViewModel.getPlants().observe(this, listResource -> {
            Plant selectedPlant = null;
            if (listResource == null || listResource.data == null) return;
            for (Plant plant : listResource.data) {
                if (plant._id == getIntent().getIntExtra(FLAG_EXTRA_PLANT_ID, -1)) {
                    selectedPlant = plant;
                }
            }
            if (selectedPlant == null) return;
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(selectedPlant.F_Name_Ch);
            }
            plantDetailFragment.setPlant(selectedPlant);
        });

    }

    private void init() {
        plantDetailFragment = PlantDetailFragment.getFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_view, plantDetailFragment);
        transaction.commit();
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

}
