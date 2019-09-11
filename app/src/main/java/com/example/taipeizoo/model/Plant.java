package com.example.taipeizoo.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plant")
public class Plant {
    public String F_Name_Latin;
    public String F_pdf02_ALT;
    public String F_Location;
    public String F_pdf01_ALT;
    public String F_Summary;
    public String F_Pic01_URL;
    public String F_pdf02_URL;
    public String F_Pic02_URL;
    public String F_Keywords;
    public String F_Code;
    public String F_Geo;
    public String F_Pic03_URL;
    public String F_Voice01_ALT;
    public String F_AlsoKnown;
    public String F_Voice02_ALT;
    public String F_Name_Ch;
    public String F_Pic04_ALT;
    public String F_Name_En;
    public String F_Brief;
    public String F_Pic04_URL;
    public String F_Voice01_URL;
    public String F_Feature;
    public String F_Pic02_ALT;
    public String F_Family;
    public String F_Voice03_ALT;
    public String F_Voice02_URL;
    public String F_Pic03_ALT;
    public String F_Pic01_ALT;
    public String F_CID;
    public String F_pdf01_URL;
    public String F_Vedio_URL;
    public String F_Genus;
    public String F_Function;
    public String F_Voice03_URL;
    public String F_Update;
    @PrimaryKey(autoGenerate = true)
    public Integer _id;
}
