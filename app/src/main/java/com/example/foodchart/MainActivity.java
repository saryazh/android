package com.example.foodchart;


import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class MainActivity extends AppCompatActivity {


    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure layout has correct button IDs

        Button btnAdd = findViewById(R.id.btn_add_food);
        Button btnSchedule = findViewById(R.id.btn_schedule_food);
         tableLayout = findViewById(R.id.tableLayout);

        btnAdd.setOnClickListener(view -> startActivity(new Intent(this, AddFoodActivity.class)));

        btnSchedule.setOnClickListener(view -> startActivity(new Intent(this, ScheduleFoodActivity.class)));

        findViewById(R.id.btnGenerateRandom).setOnClickListener(v -> {
            generateRandomChart();
            loadSchedule();
        });
        loadSchedule();
    }

    private void generateRandomChart() {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            List<FoodItem> allFoods = db.foodScheduleDao().getAllFoodItems();

            if (allFoods == null || allFoods.isEmpty()) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Please add food items first!", Toast.LENGTH_SHORT).show()
                );
                return;
            }

            db.foodScheduleDao().clearSchedule(); // if you created this method

            Random random = new Random();
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            String[] meals = {"Breakfast", "Lunch", "Dinner"};

            for (String day : days) {
                for (String meal : meals) {
                    FoodSchedule schedule = new FoodSchedule();
                    schedule.dayOfWeek = day;
                    schedule.mealType = meal;
                    FoodItem selected = allFoods.get(random.nextInt(allFoods.size()));
                    schedule.foodItemId = selected.id;
                    db.foodScheduleDao().insertSchedule(schedule);
                }
            }

            runOnUiThread(() -> {
                loadSchedule(); // update UI
                Toast.makeText(this, "Random chart generated!", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
    private void loadSchedule(){
    List<ScheduleItem> scheduleItemList = AppDatabase.getInstance(this).foodScheduleDao().getWeeklySchedule();

    Map<String, Map<String,String>> tableData = new LinkedHashMap<>();
    for(String day : Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")){
        Map<String, String> mealMap = new HashMap<>();
        mealMap.put("Breakfast", "");
        mealMap.put("Lunch", "");
        mealMap.put("Dinner", "");
        tableData.put(day, mealMap);
    }
    for(ScheduleItem item : scheduleItemList) {
    Objects.requireNonNull(tableData.get(item.dayOfWeek)).put(item.mealType, item.name);
    }

    tableLayout.removeAllViews();

    TableRow header = new TableRow(this);
    header.addView(createText("Day"));
    header.addView(createText("Breakfast"));
    header.addView(createText("Lunch"));
    header.addView(createText("Dinner"));
    tableLayout.addView(header);
for(String day : tableData.keySet()){
        TableRow row = new TableRow(this);
        row.addView(createText(day));
        row.addView(createText(Objects.requireNonNull(tableData.get(day)).get("Breakfast")));
        row.addView(createText(Objects.requireNonNull(tableData.get(day)).get("Lunch")));
        row.addView(createText(Objects.requireNonNull(tableData.get(day)).get("Dinner")));
        tableLayout.addView(row);
    }
    }

    private TextView createText(String text) {
        TextView tv = new TextView(this);
        tv.setPadding(16, 16, 16, 16);
        tv.setText(text);
        return tv;
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadSchedule();
    }
}