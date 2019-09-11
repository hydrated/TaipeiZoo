package com.example.taipeizoo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.taipeizoo.MainApplication;
import com.example.taipeizoo.R;
import com.example.taipeizoo.fragment.ZooFieldDetailFragment;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.service.Resource;
import com.example.taipeizoo.viewmodel.PlantViewModel;
import com.example.taipeizoo.viewmodel.ZooFieldViewModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZooFieldDetailActivity extends AppCompatActivity {

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

    @BindView(R.id.content_view)
    View view;

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

        // TODO: nested object searching.
        zooFieldViewModel.getZooFields().observe(this, listResource -> {
            ZooField selectedZooField = null;
            if (listResource == null || listResource.data == null) return;
            for (ZooField zooField : listResource.data) {
                if (zooField._id == getIntent().getIntExtra(FLAG_EXTRA_ZOO_FIELD_ID, -1)){
                    selectedZooField = zooField;
                }
            }
            if (selectedZooField == null) return;
            plantViewModel.getPlantsByArea(selectedZooField.E_Name).observe(this, listResource1 -> {
                Log.d("hydrated", "");
            });
        });

    }

    private void init() {
        Fragment fragment = new ZooFieldDetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_view, fragment);
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
