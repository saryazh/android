package com.example.foodchart;


import androidx.room.Dao;
import androidx.room.Insert;

import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodItemDao {
    @Insert
    void insert(FoodItem foodItem);

    @Query("SELECT * FROM food_table WHERE category = :category")
    List<FoodItem> getFoodByCategory(String category);

//    @Insert
//    void insertAll(List<FoodItem> foodItems);
//
//    void clear();

    @Query("SELECT * FROM food_table")
    List<FoodItem> getAllFoodItems();
}
