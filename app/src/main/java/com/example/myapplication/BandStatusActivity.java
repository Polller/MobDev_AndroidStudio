package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class BandStatusActivity extends AppCompatActivity {

    private LinearLayout hireLogsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_status);

        hireLogsContainer = findViewById(R.id.hireLogsContainer);

        SharedPreferences prefs = getSharedPreferences("band_status", MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();

        hireLogsContainer.removeAllViews();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            if (!key.contains("_") && entry.getValue() instanceof String) {
                String bandName = key;
                String status = (String) entry.getValue();
                String hiredOn = prefs.getString(bandName + "_hiredOn", "Unknown");
                String bookingDate = prefs.getString(bandName + "_bookingDate", "Unknown");
                addBandCard(bandName, hiredOn, bookingDate, status);
            }
        }
    }

    private void addBandCard(String nameStr, String hiredStr, String bookingStr, String statusStr) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackgroundColor(Color.parseColor("#FFFFFF"));
        card.setElevation(8f);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 32);
        card.setLayoutParams(cardParams);

        TextView name = new TextView(this);
        name.setText("Name: " + nameStr);
        name.setTextSize(16);
        name.setTextColor(Color.BLACK);
        card.addView(name);

        TextView hired = new TextView(this);
        hired.setText("Hired On: " + hiredStr);
        hired.setTextSize(16);
        hired.setTextColor(Color.BLACK);
        card.addView(hired);

        TextView booking = new TextView(this);
        booking.setText("Booking Date: " + bookingStr);
        booking.setTextSize(16);
        booking.setTextColor(Color.BLACK);
        card.addView(booking);

        TextView status = new TextView(this);
        status.setText("Status: " + statusStr);
        status.setTextSize(16);
        status.setTextColor(Color.WHITE);
        status.setGravity(Gravity.CENTER);
        status.setPadding(16, 8, 16, 8);

        switch (statusStr.toLowerCase()) {
            case "accepted":
                status.setBackgroundColor(Color.parseColor("#4CAF50"));
                break;
            case "pending":
                status.setBackgroundColor(Color.parseColor("#FFC107"));
                break;
            case "cancelled":
                status.setBackgroundColor(Color.parseColor("#F44336"));
                break;
            default:
                status.setBackgroundColor(Color.GRAY);
        }

        card.addView(status);

        hireLogsContainer.addView(card, 0);
    }
}
