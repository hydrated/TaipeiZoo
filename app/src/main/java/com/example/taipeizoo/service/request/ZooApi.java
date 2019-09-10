package com.example.taipeizoo.service.request;

import com.example.taipeizoo.service.response.ZooFieldResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZooApi {

    @GET("opendata/datalist/apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    Call<ZooFieldResponse> fetchZooFields(@Query("q") String query);

}
