package com.example.foodchart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleFoodActivity extends AppCompatActivity {
    private Spinner spinnerDay, spinnerMeal, spinnerFood;
   // private FoodViewModel viewModel;
    private List<FoodItem> foodList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_food);

        spinnerDay = findViewById(R.id.spinnerDay);
        spinnerMeal = findViewById(R.id.spinnerMeal);
        spinnerFood = findViewById(R.id.spinnerFood);
        Button buttonSchedule = findViewById(R.id.btnSchedule);

       // viewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        foodList = AppDatabase.getInstance(this).foodScheduleDao().getAllFoodItems();
        ArrayAdapter<String> foodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                foodList.stream().map(f->f.name).collect(Collectors.toList()));
        spinnerFood.setAdapter(foodAdapter);

        spinnerDay.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")));

        spinnerMeal.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList("Breakfast", "Lunch", "Dinner")));



        buttonSchedule.setOnClickListener(v -> {
            if (foodList == null || foodList.isEmpty()) return;
            FoodSchedule schedule = new FoodSchedule();
            schedule.foodItemId = foodList.get(spinnerFood.getSelectedItemPosition()).id;
            schedule.dayOfWeek = spinnerDay.getSelectedItem().toString();
            schedule.mealType = spinnerMeal.getSelectedItem().toString();

            AppDatabase.getInstance(this).foodScheduleDao().insertSchedule(schedule);
            Toast.makeText(this, "Food Scheduled!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}