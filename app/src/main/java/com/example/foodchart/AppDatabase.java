package com.example.foodchart;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FoodItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
//    public abstract FoodScheduleDao foodScheduleDao();
    public abstract FoodItemDao foodItemDao();
    private static volatile AppDatabase INSTANCE;
//    private static final int THREAD_COUNT = 4;

//    public static final ExecutorService databaseWriteExecutor =
//            Executors.newFixedThreadPool(THREAD_COUNT);

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "food_db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
