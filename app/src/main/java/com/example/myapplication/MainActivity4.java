package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_type);

        setupGenreClick(R.id.textViewGenre2, "Hip Hop");
        setupGenreClick(R.id.textViewGenre3, "Classical");
        setupGenreClick(R.id.textViewGenre4, "Rock");
        setupGenreClick(R.id.textViewGenre5, "Jazz");
        setupGenreClick(R.id.textViewGenre6, "Pop");
        setupGenreClick(R.id.textViewGenre7, "EDM");
        setupGenreClick(R.id.textViewGenre8, "Country");
    }

    private void setupGenreClick(int textViewId, final String genre) {
        TextView genreTextView = findViewById(textViewId);
        genreTextView.setOnClickListener(view -> showBandsDialog(genre));
    }

    private void showBandsDialog(String genre) {
        String[] bands;
        switch (genre) {
            case "Hip Hop":
                bands = new String[]{"Band 1: Hip Hop Group 1", "Band 2: Hip Hop Group 2", "Band 3: Hip Hop Group 3"};
                break;
            case "Classical":
                bands = new String[]{"Band 1: Classical Group 1", "Band 2: Classical Group 2", "Band 3: Classical Group 3"};
                break;
            case "Rock":
                bands = new String[]{"Band 1: Rock Group 1", "Band 2: Rock Group 2", "Band 3: Rock Group 3"};
                break;
            case "Jazz":
                bands = new String[]{"Band 1: Jazz Group 1", "Band 2: Jazz Group 2", "Band 3: Jazz Group 3"};
                break;
            case "Pop":
                bands = new String[]{"Band 1: Pop Group 1", "Band 2: Pop Group 2", "Band 3: Pop Group 3"};
                break;
            case "EDM":
                bands = new String[]{"Band 1: EDM Group 1", "Band 2: EDM Group 2", "Band 3: EDM Group 3"};
                break;
            case "Country":
                bands = new String[]{"Band 1: Country Group 1", "Band 2: Country Group 2", "Band 3: Country Group 3"};
                break;
            default:
                bands = new String[]{"No bands available"};
        }

        new AlertDialog.Builder(this)
                .setTitle("Bands for " + genre)
                .setItems(bands, (dialog, which) -> {
                    String selectedBand = bands[which];

                    // Save to SharedPreferences chat list
                    SharedPreferences prefs = getSharedPreferences("chat_prefs", MODE_PRIVATE);
                    String existingChats = prefs.getString("chat_list", "");
                    if (!existingChats.contains(selectedBand)) {
                        existingChats += selectedBand + ";";
                        prefs.edit().putString("chat_list", existingChats).apply();
                    }

                    // Open Chat List
                    Intent intent = new Intent(MainActivity4.this, ChatListActivity.class);
                    startActivity(intent);
                })
                .setPositiveButton("Close", null)
                .show();
    }
}
