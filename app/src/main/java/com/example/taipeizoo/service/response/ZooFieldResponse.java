package com.example.taipeizoo.service.response;

import com.example.taipeizoo.model.ZooField;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZooFieldResponse {

    @SerializedName("result")
    public Result result;

    private static class Result {
        public Integer limit;
        public Integer offset;
        public String sort;
        public List<ZooField> results;
    }

    public List<ZooField> getZooFields() {
        return result.results;
    }
}

