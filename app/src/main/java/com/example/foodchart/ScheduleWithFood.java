package com.example.foodchart;


import androidx.room.Embedded;
import androidx.room.Relation;



public class ScheduleWithFood {
    @Embedded
    public FoodSchedule schedule;

    @Relation(
            parentColumn = "foodItemId",
            entityColumn = "id"
    )
    public FoodItem foodItem;


}
