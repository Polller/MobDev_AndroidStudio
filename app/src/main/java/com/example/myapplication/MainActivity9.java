package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity9 extends AppCompatActivity {

    private LinearLayout hireLogsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer_logs);

        hireLogsContainer = findViewById(R.id.hireLogsContainer);

        SharedPreferences prefs = getSharedPreferences("log_data", MODE_PRIVATE);
        String allLogs = prefs.getString("logs", "");

        if (!allLogs.isEmpty()) {
            String[] entries = allLogs.split("\\|\\|"); // Split by our delimiter
            for (String entry : entries) {
                addHireLog(entry);
            }
        } else {
            addHireLog("No application history found.");
        }
    }

    private void addHireLog(String text) {
        TextView logEntry = new TextView(this);
        logEntry.setText(text);
        logEntry.setTextSize(16f);
        logEntry.setTextColor(getResources().getColor(android.R.color.black));
        logEntry.setBackgroundColor(getResources().getColor(android.R.color.white));
        logEntry.setPadding(30, 20, 30, 20);
        logEntry.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 24);
        logEntry.setLayoutParams(params);

        hireLogsContainer.addView(logEntry);
    }
}
