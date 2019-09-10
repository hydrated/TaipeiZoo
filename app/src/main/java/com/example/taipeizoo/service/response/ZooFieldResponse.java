package com.example.taipeizoo.service.response;

import com.example.taipeizoo.model.ZooField;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZooFieldResponse {

    @SerializedName("results")
    private List<ZooField> zooFields;

    public List<ZooField> getZooFields() {
        return zooFields;
    }

    @SuppressWarnings("unused")
    public void setZooFields(List<ZooField> zooFields) {
        this.zooFields = zooFields;
    }
}

