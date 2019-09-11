package com.example.taipeizoo.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taipeizoo.model.Plant;
import com.example.taipeizoo.model.ZooField;

@Database(entities = {ZooField.class, Plant.class}, version = 1, exportSchema = false)
public abstract class ZooDatabase extends RoomDatabase {
    public abstract ZooFieldDao getZooFieldDao();

    public abstract PlantDao getPlantDao();
}
