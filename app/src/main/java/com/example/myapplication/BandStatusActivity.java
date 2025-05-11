package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BandStatusActivity extends AppCompatActivity {

    private LinearLayout hireLogsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_status);

        hireLogsContainer = findViewById(R.id.hireLogsContainer);

        String[][] bandData = {
                {"The Rocking Beats", "Rock", "2025-05-01 at 3:00 PM", "2025-05-10", "Accepted"},
                {"DJ SonicWave", "Electronic", "2025-04-25 at 10:00 AM", "2025-05-03", "Cancelled"},
                {"Acoustic Flow", "Acoustic", "2025-04-15 at 1:30 PM", "2025-04-20", "Accepted"},
                {"Jazz Pulse", "Jazz", "2025-03-28 at 5:00 PM", "2025-04-05", "Pending"},
                {"Electric Storm", "EDM", "2025-03-15 at 2:00 PM", "2025-03-22", "Cancelled"},
                {"Folk Harmony", "Folk", "2025-03-01 at 11:00 AM", "2025-03-10", "Accepted"},
                {"The Vocal Vibes", "Pop", "2025-02-20 at 4:30 PM", "2025-03-01", "Pending"}
        };

        for (String[] band : bandData) {
            addBandCard(band);
        }
    }

    private void addBandCard(String[] band) {
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
        name.setText("Name: " + band[0]);
        name.setTextSize(16);
        name.setTextColor(Color.BLACK);
        card.addView(name);

        TextView genre = new TextView(this);
        genre.setText("Genre: " + band[1]);
        genre.setTextSize(16);
        genre.setTextColor(Color.BLACK);
        card.addView(genre);

        TextView hired = new TextView(this);
        hired.setText("Hired On: " + band[2]);
        hired.setTextSize(16);
        hired.setTextColor(Color.BLACK);
        card.addView(hired);

        TextView booking = new TextView(this);
        booking.setText("Booking Date: " + band[3]);
        booking.setTextSize(16);
        booking.setTextColor(Color.BLACK);
        card.addView(booking);

        TextView status = new TextView(this);
        status.setText("Status: " + band[4]);
        status.setTextSize(16);
        status.setPadding(0, 10, 0, 0);
        status.setTextColor(Color.WHITE);
        status.setGravity(Gravity.CENTER);
        status.setPadding(16, 8, 16, 8);

        switch (band[4].toLowerCase()) {
            case "accepted":
                status.setBackgroundColor(Color.parseColor("#4CAF50")); // Green
                break;
            case "pending":
                status.setBackgroundColor(Color.parseColor("#FFC107")); // Amber
                break;
            case "cancelled":
                status.setBackgroundColor(Color.parseColor("#F44336")); // Red
                break;
            default:
                status.setBackgroundColor(Color.GRAY);
        }

        card.addView(status);
        hireLogsContainer.addView(card);
    }
}
