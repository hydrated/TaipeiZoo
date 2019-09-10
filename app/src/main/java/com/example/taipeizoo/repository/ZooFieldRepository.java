package com.example.taipeizoo.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.taipeizoo.db.ZooFieldDao;
import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.service.NetworkBoundResource;
import com.example.taipeizoo.service.Resource;
import com.example.taipeizoo.service.request.ZooApi;
import com.example.taipeizoo.service.response.ZooFieldResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class ZooFieldRepository {

    private final ZooFieldDao zooFieldDao;
    private final ZooApi zooApi;

    @Inject
    ZooFieldRepository(ZooFieldDao dao, ZooApi service) {
        this.zooFieldDao = dao;
        this.zooApi = service;
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

}
