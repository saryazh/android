package com.example.foodchart;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = FoodItem.class,
        parentColumns = "id", childColumns = "foodItemId", onDelete = ForeignKey.CASCADE))
public class FoodSchedule {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String dayOfWeek;
    public String mealType;
    public int foodItemId;
}
