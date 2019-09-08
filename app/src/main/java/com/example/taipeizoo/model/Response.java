package com.example.taipeizoo.model;

import com.example.taipeizoo.service.request.ZooApi;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("result")
    public Result result;

    public static class Result{
        public Integer limit;
        public Integer offset;
        public String sort;
        public List<ZooField> results;
    }
}
