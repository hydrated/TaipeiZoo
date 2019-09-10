package com.example.taipeizoo.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
