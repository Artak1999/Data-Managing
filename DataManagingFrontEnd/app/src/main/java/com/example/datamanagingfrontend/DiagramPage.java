package com.example.datamanagingfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiagramPage extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram_page);
        barChart = findViewById(R.id.diagram);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        List<String> dataFromScraping = (ArrayList<String>) bundle.getSerializable("list");
        Log.d("TAG","doInBack: " + dataFromScraping);
        int[] countOfTasks = new int[12];
        int count = 0;
        for (int i = 0; i < dataFromScraping.size(); i++) {
            if (count < 12 && dataFromScraping.get(i).startsWith("+"))
                countOfTasks[count] += 1;
            if (count == 11)
                count = -1;
            count++;
        }
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < countOfTasks.length; i++)
            barEntries.add(new BarEntry(countOfTasks[i],i));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Task");
        ArrayList<String> tasks = new ArrayList<>();
        String[] taskName = {"A","B","C","D","E","F","G","H","I","J","K","L"};
        tasks.addAll(Arrays.asList(taskName));
        BarData barData = new BarData(tasks,barDataSet);
        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }
}