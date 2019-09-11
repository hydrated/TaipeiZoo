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
    private final ZooRepository zooRepository;

    @Inject
    public PlantViewModel(ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
        plants = zooRepository.loadAllPlants();
    }

    public LiveData<Resource<List<Plant>>> getPlants() {
        return plants;
    }

    //TODO: cache
    public LiveData<List<Plant>> getPlantsByArea(String area) {
        return zooRepository.getPlantsByArea(area);
    }
}
