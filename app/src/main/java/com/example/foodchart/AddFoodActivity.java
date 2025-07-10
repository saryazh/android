package com.example.foodchart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddFoodActivity extends AppCompatActivity {
   //private FoodViewModel foodViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        EditText editName = findViewById(R.id.editName);
        Button buttonAdd = findViewById(R.id.btnAddFood);
        EditText editCategory = findViewById(R.id.editCategory);
        //foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        buttonAdd.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String category = editCategory.getText().toString();

            if (!name.isEmpty()) {
                FoodItem item = new FoodItem();
                item.name = name;
                item.category = category;
           //     foodViewModel.insertFood(item);
                AppDatabase.getInstance(this).foodScheduleDao().insertFood(item);
                Toast.makeText(this, "Food added!", Toast.LENGTH_SHORT).show();
                finish(); // go back
            } else {
                Toast.makeText(this, "Enter food name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

