package com.example.foodchart;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<ScheduleWithFood> data = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setScheduleList(List<ScheduleWithFood> list) {
        this.data = list;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(android.R.id.text1);
        }
    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        ScheduleWithFood item = data.get(position);
        String display = item.schedule.dayOfWeek + "\n" +
                item.schedule.mealType + ": " +
                item.foodItem.name;
        holder.textView.setText(display);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
