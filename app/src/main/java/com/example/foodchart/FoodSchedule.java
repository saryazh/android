package com.example.foodchart;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(
                entity = FoodItem.class,
                parentColumns = "id",
                childColumns = "foodItemId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("foodItemId")}
)
public class FoodSchedule {
    @PrimaryKey(autoGenerate = true)
    public int schedule_id;

    public String dayOfWeek;
    public String mealType;

    public int foodItemId;

    public FoodSchedule(@NonNull String dayOfWeek, @NonNull String mealType, int foodItemId) {
        this.dayOfWeek = dayOfWeek;
        this.mealType = mealType;
        this.foodItemId = foodItemId;
    }
}