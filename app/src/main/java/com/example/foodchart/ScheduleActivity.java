package com.example.foodchart;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ScheduleActivity extends AppCompatActivity {
        TableLayout tableLayout;
        FoodViewModel viewModel;
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[] meals = {"Breakfast", "Lunch", "Dinner","Snack"};
        Map<String, List<FoodItem>> categoryMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_schedule);

            tableLayout = findViewById(R.id.tableLayout);
            viewModel = new ViewModelProvider(this).get(FoodViewModel.class);

            loadFoodData();
            displayChart();
        }

        private void loadFoodData() {
            categoryMap = new HashMap<>();
            for (String meal : meals) {
                categoryMap.put(meal, viewModel.getFoodByCategory(meal));
            }
        }

        private void displayChart() {
            tableLayout.removeAllViews();

            // Top row (header): [empty], Breakfast, Lunch, Dinner
            TableRow headerRow = new TableRow(this);
            headerRow.addView(createCellBold("")); // top-left corner

            for (String meal : meals) {
                headerRow.addView(createCellBold(meal));
            }
            tableLayout.addView(headerRow);

            // Rows: Mon to Sun
            for (String day : days) {
                TableRow row = new TableRow(this);
                row.addView(createCellBold(day)); // Day label at start

                for (String meal : meals) {
                    TextView cell = createCell("-");

                    List<FoodItem> items = categoryMap.get(meal);
                    if (items != null && !items.isEmpty()) {
                        int rand = new Random().nextInt(items.size());
                        cell.setText(items.get(rand).name);
                    }

                    row.addView(cell);
                }

                tableLayout.addView(row);
            }
        }

        private TextView createCell(String text) {
            TextView tv = new TextView(this);
            tv.setText(text);
            tv.setPadding(16, 12, 16, 12);
            tv.setBackgroundResource(android.R.drawable.editbox_background_normal);
            return tv;
        }

        private TextView createCellBold(String text) {
            TextView tv = createCell(text);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            return tv;
        }
    }

//
//    TableLayout tableLayout;
//    FoodViewModel viewModel;
//    String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
//    String[] meals = {"Breakfast", "Lunch", "Dinner","Snack"};
//    Map<String, List<FoodItem>> categoryMap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_schedule);
//
//        tableLayout = findViewById(R.id.tableLayout);
//        viewModel = new ViewModelProvider(this).get(FoodViewModel.class);
//
//        generateChart();
//    }
//
//    private void generateChart() {
//        categoryMap = new HashMap<>();
//        for (String meal : meals) {
//            categoryMap.put(meal, viewModel.getFoodByCategory(meal));
//        }
//
//        for (String meal : meals) {
//            TableRow row = new TableRow(this);
//            TextView mealCell = new TextView(this);
//            mealCell.setText(meal);
//            mealCell.setPadding(12, 12, 12, 12);
//            row.addView(mealCell);
//
//            for (String day : days) {
//                TextView cell = new TextView(this);
//                cell.setPadding(12, 12, 12, 12);
//                List<FoodItem> foods = categoryMap.get(meal);
//                if (foods != null && !foods.isEmpty()) {
//                    int randomIndex = new Random().nextInt(foods.size());
//                    cell.setText(foods.get(randomIndex).name);
//                } else {
//                    cell.setText("-");
//                }
//                row.addView(cell);
//            }
//            tableLayout.addView(row);
//        }
//    }
//}

//    private final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
//    private final String[] MEALS = {"Breakfast", "Lunch", "Dinner"};
//    private FoodViewModel viewModel;
//    private TableLayout tableLayout;
//    private Button btnRegenerate;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_schedule); // should contain TableLayout + buttons
//
//        tableLayout = findViewById(R.id.tableLayout);
//       //btnRegenerate = findViewById(R.id.btnRegenerate);
//
//        viewModel = new ViewModelProvider(this).get(FoodViewModel.class);
//
//        viewModel.getWeeklySchedule().observe(this, this::displaySchedule);
//
////        btnRegenerate.setOnClickListener(v -> {
////            viewModel.generateRandomSchedule(); // Replace with your logic
////        });
//
//
//    }
//
//    private void displaySchedule(List<ScheduleWithFood> scheduleList) {
//        tableLayout.removeAllViews();
//
//        addTableHeader();
//
//        for (ScheduleWithFood item : scheduleList) {
//            TableRow row = new TableRow(this);
//
//            TextView dayView = new TextView(this);
//            dayView.setText(item.schedule.dayOfWeek);
//            row.addView(dayView);
//
//            TextView mealView = new TextView(this);
//            mealView.setText(item.schedule.mealType);
//            row.addView(mealView);
//
//            TextView foodView = new TextView(this);
//            foodView.setText(item.foodItem != null ? item.foodItem.name + " (" + item.foodItem.category + ")" : "N/A");
//            row.addView(foodView);
//
//            tableLayout.addView(row);
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    private void addTableHeader() {
//        TableRow header = new TableRow(this);
//
//        TextView dayHeader = new TextView(this);
//        dayHeader.setText("Day");
//        header.addView(dayHeader);
//
//        TextView mealHeader = new TextView(this);
//        mealHeader.setText("Meal");
//        header.addView(mealHeader);
//
//        TextView foodHeader = new TextView(this);
//        foodHeader.setText("Food Item");
//        header.addView(foodHeader);
//
//        tableLayout.addView(header);
//    }
//}