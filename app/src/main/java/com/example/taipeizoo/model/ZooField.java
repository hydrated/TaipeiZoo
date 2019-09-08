package com.example.taipeizoo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "zoofields")
public class ZooField {

    public String E_Pic_URL;
    public String E_Geo;
    public String E_Info;
    public String E_no;

    public String E_Category;
    public String E_Name;
    public String E_Memo;
    @PrimaryKey(autoGenerate = true)
    public Integer _id;
    public String E_URL;

}
