package com.example.taipeizoo.service.response;

import com.example.taipeizoo.model.Plant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantResponse {

    @SerializedName("result")
    public Result result;

    private static class Result {
        public Integer limit;
        public Integer offset;
        public String sort;
        public List<Plant> results;
    }

    public List<Plant> getPlants() {
        return result.results;
    }
}

