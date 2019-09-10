package com.example.taipeizoo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.taipeizoo.model.ZooField;
import com.example.taipeizoo.repository.ZooFieldRepository;
import com.example.taipeizoo.service.Resource;

import java.util.List;

import javax.inject.Inject;

public class ZooFieldViewModel extends ViewModel {
    private final LiveData<Resource<List<ZooField>>> zooFields;

    @Inject
    public ZooFieldViewModel(ZooFieldRepository zooFieldRepository) {
        zooFields = zooFieldRepository.loadZooFields();
    }

    public LiveData<Resource<List<ZooField>>> getZooFields() {
        return zooFields;
    }
}
