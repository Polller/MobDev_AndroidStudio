package com.example.myapplication;

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

        addHireLog("The Rocking Beats", "Rock", "2025-05-01", "3:00 PM", "2025-05-10");
        addHireLog("DJ SonicWave", "Electronic", "2025-04-25", "10:00 AM", "2025-05-03");
        addHireLog("Acoustic Flow", "Acoustic", "2025-04-15", "1:30 PM", "2025-04-20");
        addHireLog("Jazz Pulse", "Jazz", "2025-03-28", "5:00 PM", "2025-04-05");
        addHireLog("Electric Storm", "EDM", "2025-03-15", "2:00 PM", "2025-03-22");
        addHireLog("Folk Harmony", "Folk", "2025-03-01", "11:00 AM", "2025-03-10");
        addHireLog("The Vocal Vibes", "Pop", "2025-02-20", "4:30 PM", "2025-03-01");
        addHireLog("Neo Classical", "Classical", "2025-02-05", "12:00 PM", "2025-02-12");
        addHireLog("Synthwave Dreams", "Synthwave", "2025-01-25", "6:45 PM", "2025-02-01");
        addHireLog("Funky Frequencies", "Funk", "2025-01-10", "9:15 AM", "2025-01-17");
        addHireLog("Reggae Roots", "Reggae", "2024-12-28", "10:45 AM", "2025-01-05");
        addHireLog("Latin Fire", "Latin", "2024-12-15", "3:20 PM", "2024-12-22");
        addHireLog("Indie Illusion", "Indie", "2024-12-01", "1:10 PM", "2024-12-10");
    }

    private void addHireLog(String name, String genre, String hiredDate, String hiredTime, String bookedDate) {
        TextView logEntry = new TextView(this);
        logEntry.setText(
                "🎤 Name: " + name + "\n" +
                        "🎼 Genre: " + genre + "\n" +
                        "📅 Hired On: " + hiredDate + " at " + hiredTime + "\n" +
                        "📌 Booking Date: " + bookedDate
        );
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