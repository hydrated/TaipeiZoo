package com.example.taipeizoo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.taipeizoo.model.Plant;
import com.example.taipeizoo.repository.ZooRepository;
import com.example.taipeizoo.service.Resource;

import java.util.List;

import javax.inject.Inject;

public class PlantViewModel extends ViewModel {
    private final LiveData<Resource<List<Plant>>> plants;

    @Inject
    public PlantViewModel(ZooRepository zooRepository) {
        plants = zooRepository.loadAllPlants();
    }

    public LiveData<Resource<List<Plant>>> getPlants() {
        return plants;
    }
}
