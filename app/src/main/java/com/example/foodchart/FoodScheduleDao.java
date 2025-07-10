package com.example.foodchart;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface FoodScheduleDao {
    @Insert
    void insertFood(FoodItem foodItem);

    @Insert
    void insertSchedule(FoodSchedule schedule);

    @Query("SELECT * FROM FoodItem")
    List<FoodItem> getAllFoodItems();

    @Query("SELECT fs.dayOfWeek, fs.mealType, fi.name, fi.category " +
            "FROM FoodSchedule fs INNER JOIN FoodItem fi ON fs.foodItemId = fi.id")
    List<ScheduleItem> getWeeklySchedule();

    @Query("DELETE FROM FoodSchedule")
    void clearSchedule();
}