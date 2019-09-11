package com.example.taipeizoo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taipeizoo.R;
import com.example.taipeizoo.model.Plant;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.ui.PlantListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlantListFragment extends Fragment {

    public static PlantListFragment getFragment() {
        return new PlantListFragment();
    }

    @BindView(R.id.plant_list_view)
    PlantListView plantListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_list, container, false);

        if (view != null) ButterKnife.bind(this, view);

        return (view != null ? view : super.onCreateView(inflater, container, savedInstanceState));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setPlants(List<Plant> data) {
        plantListView.setplantList(data);
    }
}
