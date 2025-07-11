package com.example.foodchart;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteColumn;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
@Dao
public interface FoodScheduleDao {

    @Transaction
    @Query("SELECT * FROM FoodSchedule ORDER BY dayOfWeek, mealType")
    LiveData<List<ScheduleWithFood>> getWeeklySchedule();

   @Insert
    void insertSchedule(FoodSchedule schedule);



    @Query("SELECT * FROM FoodItem")
    LiveData<List<FoodItem>> getAllFoodItems();

    @Insert
    void insertFoodSchedule(FoodSchedule schedule);

    @Insert
    void insertFoodItem(FoodItem item);
    @Query("DELETE FROM FoodSchedule")
    void clearSchedule();
}