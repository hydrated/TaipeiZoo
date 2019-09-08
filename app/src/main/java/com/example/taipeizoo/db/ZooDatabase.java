package com.example.taipeizoo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.taipeizoo.model.ZooField;

@Database(entities = {ZooField.class}, version = 1)
public abstract class ZooDatabase extends RoomDatabase {
    public abstract ZooFieldDao getZooFieldDao();
}
