package com.example.taipeizoo.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.repository.ZooRepository;
import com.example.taipeizoo.service.Resource;

import java.util.List;

import javax.inject.Inject;

public class ZooFieldViewModel extends ViewModel {
    private final LiveData<Resource<List<ZooField>>> zooFields;

    @Inject
    public ZooFieldViewModel(ZooRepository zooRepository) {
        zooFields = zooRepository.loadZooFields();
    }

    public LiveData<Resource<List<ZooField>>> getZooFields() {
        return zooFields;
    }
}
