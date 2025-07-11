package com.example.foodchart;

import android.app.Application;

import java.util.List;

public class FoodRepository {

    private final FoodItemDao foodItemDao;

    public FoodRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
     foodItemDao = db.foodItemDao();
    }

    public void insert(FoodItem item) {
        foodItemDao.insert(item);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemDao.getAllFoodItems();
    }

    public List<FoodItem> getFoodByCategory(String category) {
        return foodItemDao.getFoodByCategory(category);
    }
}
//
//public class FoodRepository {
//    private final FoodScheduleDao dao;
//    private final LiveData<List<ScheduleWithFood>> weeklySchedule;
//    private final ExecutorService executorService;
//
//    public FoodRepository(Application app) {
//        AppDatabase db = AppDatabase.getInstance(app);
//        dao = db.foodScheduleDao();
//        weeklySchedule = dao.getWeeklySchedule();
//       executorService = Executors.newSingleThreadExecutor();
//    }
//
//    public LiveData<List<ScheduleWithFood>> getWeeklySchedule() {
//        return weeklySchedule;
//    }
//
//    public LiveData<List<FoodItem>> getAllFoodItems() {
//        return dao.getAllFoodItems();
//    }
//
//    public void insertFood(FoodItem item) {
//        AppDatabase.databaseWriteExecutor.execute(() -> dao.insertFoodItem(item));
//    }
//    public void insertFoodSchedule(FoodSchedule schedule) {
//        executorService.execute(() -> dao.insertFoodSchedule(schedule));
//    }
//    public void clearSchedule() {
//        executorService.execute(dao::clearSchedule);
//    }
//    public void insertAllSchedules(List<FoodSchedule> schedules) {
//        executorService.execute(() -> {
//            for (FoodSchedule schedule : schedules) {
//                dao.insertFoodSchedule(schedule);
//            }
//        });
//    }
//    public void regenerateWeeklySchedule(List<FoodItem> items) {
//        AppDatabase.databaseWriteExecutor.execute(() -> {
//            dao.clearSchedule();
//            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//            String[] meals = {"Breakfast", "Lunch", "Dinner"};
//            Random random = new Random();
//
//            for (String day : days) {
//                for (String meal : meals) {
//                    List<FoodItem> filtered = new ArrayList<>();
//                    for (FoodItem i : items) {
//                        if (i.category.equalsIgnoreCase(meal)) {
//                            filtered.add(i);
//                        }
//                    }
//                    if (!filtered.isEmpty()) {
//                        FoodItem randomItem = filtered.get(random.nextInt(filtered.size()));
//                        dao.insertSchedule(new FoodSchedule(day, meal, randomItem.id));
//                    }
//                }
//            }
//        });
//    }
//}
