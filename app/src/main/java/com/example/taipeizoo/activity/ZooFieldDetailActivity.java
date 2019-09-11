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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.taipeizoo.MainApplication;
import com.example.taipeizoo.R;
import com.example.taipeizoo.fragment.ZooFieldDetailFragment;
import com.example.taipeizoo.viewmodel.PlantViewModel;
import com.example.taipeizoo.viewmodel.ZooFieldViewModel;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZooFieldDetailActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ZooFieldDetailActivity.class);
        context.startActivity(intent);
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private PlantViewModel plantViewModel;

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

        plantViewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantViewModel.class);
        plantViewModel.getPlants().observe(this, listResource -> {
            Log.d("hydrated", "");
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
