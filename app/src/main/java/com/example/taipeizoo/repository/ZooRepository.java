package com.example.taipeizoo.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.taipeizoo.db.PlantDao;
import com.example.taipeizoo.db.ZooFieldDao;
import com.example.taipeizoo.model.Plant;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.service.NetworkBoundResource;
import com.example.taipeizoo.service.Resource;
import com.example.taipeizoo.service.request.ZooApi;
import com.example.taipeizoo.service.response.PlantResponse;
import com.example.taipeizoo.service.response.ZooFieldResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class ZooRepository {

    private final ZooFieldDao zooFieldDao;
    private final PlantDao plantDao;
    private final ZooApi zooApi;

    @Inject
    ZooRepository(ZooFieldDao zooFieldDao, ZooApi service, PlantDao plantDao) {
        this.zooFieldDao = zooFieldDao;
        this.zooApi = service;
        this.plantDao = plantDao;
    }

    public LiveData<Resource<List<ZooField>>> loadZooFields() {
        return new NetworkBoundResource<List<ZooField>, ZooFieldResponse>() {

            @Override
            protected void saveCallResult(ZooFieldResponse item) {
                if (null != item && item.getZooFields() != null)
                    zooFieldDao.insert(item.getZooFields());
            }

            @NonNull
            @Override
            protected LiveData<List<ZooField>> loadFromDb() {
                return zooFieldDao.getAllZooFieldsAsLiveData();
            }

            @NonNull
            @Override
            protected Call<ZooFieldResponse> createCall() {
                return zooApi.fetchZooFields("");
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<Plant>>> loadAllPlants() {
        return new NetworkBoundResource<List<Plant>, PlantResponse>() {

            @Override
            protected void saveCallResult(PlantResponse item) {
                if (null != item && item.getPlants() != null)
                    plantDao.insert(item.getPlants());
            }

            @NonNull
            @Override
            protected LiveData<List<Plant>> loadFromDb() {
                return plantDao.getPlantsAsLiveData();
            }

            @NonNull
            @Override
            protected Call<PlantResponse> createCall() {
                return zooApi.fetchPlants("");
            }
        }.getAsLiveData();
    }

    public LiveData<List<Plant>> getPlantsByArea(String area) {
        return plantDao.getPlantsByAreaAsLiveData("%" + area + "%");
    }

}
