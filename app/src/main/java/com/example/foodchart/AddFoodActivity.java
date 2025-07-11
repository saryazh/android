package com.example.foodchart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class AddFoodActivity extends AppCompatActivity {
    private FoodViewModel foodViewModel;
    Spinner spinnerCategory;
    String selectedCategory;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        EditText editName = findViewById(R.id.editName);
        Button buttonAdd = findViewById(R.id.btnAddFood);
        //EditText editCategory = findViewById(R.id.editCategory);
        //foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        spinnerCategory = findViewById(R.id.spinnerCategory);

// Example categories
        String[] categories = {"Breakfast", "Lunch", "Dinner", "Snack"};

// Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

// Handle selection
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = categories[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategory = "Uncategorized";
            }
        });
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        buttonAdd.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String category = selectedCategory;

            if (!name.isEmpty() && !category.isEmpty()) {
                foodViewModel.insert(new FoodItem(name, category));
                Toast.makeText(this, "Food Added", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

