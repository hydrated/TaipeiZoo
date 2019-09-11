package com.example.taipeizoo.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.taipeizoo.model.Plant;

import java.util.List;

@Dao
public interface PlantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Plant plant);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Plant> plants);

    @Query("SELECT * FROM plant")
    public Plant[] getPlants();

    @Query("SELECT * FROM plant")
    LiveData<List<Plant>> getPlantsAsLiveData();

    @Query("SELECT * FROM plant where F_Location LIKE :area")
    LiveData<List<Plant>> getPlantsByAreaAsLiveData(String area);
}