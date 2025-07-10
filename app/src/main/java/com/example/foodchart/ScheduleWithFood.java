package com.example.foodchart;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

public class ScheduleWithFood {


    @Embedded
    public FoodSchedule schedule;

    @Relation(
            parentColumn = "food_item_id", // from FoodSchedule
            entityColumn = "food_item_id"  // from FoodItem
    )
    public FoodItem foodItem;
}
