package com.example.taipeizoo.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.taipeizoo.model.ZooField;

import java.util.List;

@Dao
public interface ZooFieldDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ZooField zooField);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ZooField... zooFields);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ZooField> zooFields);

    @Query("SELECT * FROM zoofields")
    public ZooField[] getAllZooFields();

    @Query("SELECT * FROM zoofields")
    LiveData<List<ZooField>> getAllZooFieldsAsLiveData();
}
