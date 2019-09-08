package com.example.taipeizoo.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.taipeizoo.model.ZooField;

@Dao
public interface ZooFieldDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ZooField zooField);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ZooField... zooFields);

    @Query("SELECT * FROM zoofields")
    public ZooField[] getAllZooFields();
}
