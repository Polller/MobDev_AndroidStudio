package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    String[] bands = {
            "Shadow Notes Band",
            "Echo Pulse",
            "Neon Rhythms",
            "The Bass Breakers",
            "Crimson Sound",
            "Funky Strums",
            "Golden Mic",
            "Silent Amp"
    };

    String[] descriptions = {
            "📍 Location: Manila, Philippines\n🎶 Genre: Indie Rock\n💰 Price: ₱25,000 - ₱35,000 per show",
            "📍 Location: Quezon City, Philippines\n🎶 Genre: Synth Pop\n💰 Price: ₱18,000 - ₱28,000 per show",
            "📍 Location: Cebu City, Philippines\n🎶 Genre: Dance Rock / Electronic\n💰 Price: ₱30,000 - ₱40,000 per show",
            "📍 Location: Davao City, Philippines\n🎶 Genre: Funk Rock\n💰 Price: ₱22,000 - ₱32,000 per show",
            "📍 Location: Baguio City, Philippines\n🎶 Genre: Alternative Metal\n💰 Price: ₱27,000 - ₱38,000 per show",
            "📍 Location: Makati, Philippines\n🎶 Genre: Soul / Funk\n💰 Price: ₱20,000 - ₱30,000 per show",
            "📍 Location: Tagaytay, Philippines\n🎶 Genre: Acoustic Pop\n💰 Price: ₱15,000 - ₱25,000 per show",
            "📍 Location: Iloilo City, Philippines\n🎶 Genre: Ambient Rock\n💰 Price: ₱19,000 - ₱29,000 per show"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_hiring_band);

        ListView bandListView = findViewById(R.id.bandListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bands);
        bandListView.setAdapter(adapter);

        bandListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedBand = bands[position];
            String description = descriptions[position];
            showBandDescriptionDialog(selectedBand, description);
        });
    }

    private void showBandDescriptionDialog(String bandName, String description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(bandName);

        // Band description
        TextView descriptionView = new TextView(this);
        descriptionView.setText(description);
        descriptionView.setPadding(30, 30, 30, 30);
        descriptionView.setTextSize(16f);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(descriptionView);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(scrollView);

        // Hire button
        Button hireButton = new Button(this);
        hireButton.setText("Hire Band");
        hireButton.setOnClickListener(v -> {
            // Save to SharedPreferences
            SharedPreferences prefs = getSharedPreferences("chat_prefs", MODE_PRIVATE);
            String existingChats = prefs.getString("chat_list", "");
            if (!existingChats.contains(bandName)) {
                existingChats += bandName + ";";
                prefs.edit().putString("chat_list", existingChats).apply();
            }

            // Go to ChatListActivity
            Intent intent = new Intent(MainActivity3.this, ChatListActivity.class);
            startActivity(intent);
        });

        layout.addView(hireButton);
        layout.setPadding(30, 30, 30, 30);

        builder.setView(layout);
        builder.setNegativeButton("Close", null);
        builder.show();
    }
}
