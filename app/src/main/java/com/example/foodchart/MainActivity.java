package com.example.foodchart;


import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure layout has correct button IDs

        Button btnAdd = findViewById(R.id.btn_add_food);
        Button btnSchedule = findViewById(R.id.btn_schedule_food);


        btnAdd.setOnClickListener(view -> startActivity(new Intent(this, AddFoodActivity.class)));

        btnSchedule.setOnClickListener(view -> startActivity(new Intent(this, ScheduleActivity.class)));

    }
}