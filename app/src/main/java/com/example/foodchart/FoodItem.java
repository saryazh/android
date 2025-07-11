package com.example.foodchart;


import androidx.annotation.NonNull;
import androidx.room.Entity;

import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class FoodItem {
    @PrimaryKey(autoGenerate = true)

    public int id;

    public String name;
    public String category;

    public FoodItem(@NonNull String name, @NonNull String category) {
        this.name = name;
        this.category = category;
    }
}