package com.example.foodchart;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {FoodItem.class, FoodSchedule.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract FoodScheduleDao foodScheduleDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "food_chart_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
