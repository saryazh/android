package com.example.foodchart;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodItemDao {
    @Insert
    void insert(FoodItem foodItem);

    @Query("SELECT * FROM FoodItem")
    LiveData<List<FoodItem>> getAllFoodItems();
    @Query("SELECT * FROM FoodItem")
    List<FoodItem> getAllFoodItemsNow();
}
