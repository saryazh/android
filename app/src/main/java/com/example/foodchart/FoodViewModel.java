package com.example.foodchart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private final FoodRepository repository;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        repository = new FoodRepository(application);
    }

    public void insert(FoodItem item) {
        repository.insert(item);
    }

    public List<FoodItem> getAllFoodItems() {
        return repository.getAllFoodItems();
    }

    public List<FoodItem> getFoodByCategory(String category) {
        return repository.getFoodByCategory(category);
    }
}


//public class FoodViewModel extends AndroidViewModel {
//    private final FoodRepository repository;
//    private final LiveData<List<ScheduleWithFood>> weeklySchedule;
//    private final LiveData<List<FoodItem>> allFoodItems;
//
//    public FoodViewModel(@NonNull Application app) {
//        super(app);
//        repository = new FoodRepository(app);
//        weeklySchedule = repository.getWeeklySchedule();
//        allFoodItems = repository.getAllFoodItems();
//    }
//
//    public LiveData<List<ScheduleWithFood>> getWeeklySchedule() {
//        return weeklySchedule;
//    }
//
//    public LiveData<List<FoodItem>> getAllFoodItems() {
//        return allFoodItems;
//    }
//
//    public void insertFood(FoodItem item) {
//        repository.insertFood(item);
//    }
//
//
//    public void regenerateChart(List<FoodSchedule> schedules) {
//        repository.clearSchedule();
//        repository.insertAllSchedules(schedules);
//    }
//    public void regenerateSchedule(List<FoodItem> items) {
//        repository.regenerateWeeklySchedule(items);
//    }
//}

