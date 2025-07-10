package com.example.foodchart;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FoodItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String category;
}
