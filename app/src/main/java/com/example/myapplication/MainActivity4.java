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
                bands = new String[]{"Wu-Tang Clan", "Run-D.M.C.", "N.W.A"};
                break;
            case "Classical":
                bands = new String[]{"Berlin Philharmonic", "Kronos Quartet", "The Piano Guys"};
                break;
            case "Rock":
                bands = new String[]{"Queen", "Foo Fighters", "Arctic Monkeys"};
                break;
            case "Jazz":
                bands = new String[]{"Snarky Puppy", "The Dave Brubeck Quartet", "Weather Report"};
                break;
            case "Pop":
                bands = new String[]{"Maroon 5", "BTS", "OneRepublic"};
                break;
            case "EDM":
                bands = new String[]{"Swedish House Mafia", "The Chainsmokers", "Zeds Dead"};
                break;
            case "Country":
                bands = new String[]{"Zac Brown Band", "Lady A (formerly Lady Antebellum)", "Florida Georgia Line"};
                break;
            default:
                bands = new String[]{"No bands available"};
        }

        new AlertDialog.Builder(this)
                .setTitle("Bands for " + genre)
                .setItems(bands, (dialog, which) -> {
                    String selectedBand = bands[which];

                    SharedPreferences prefs = getSharedPreferences("chat_prefs", MODE_PRIVATE);
                    String existingChats = prefs.getString("chat_list", "");
                    if (!existingChats.contains(selectedBand)) {
                        existingChats += selectedBand + ";";
                        prefs.edit().putString("chat_list", existingChats).apply();
                    }

                    Intent intent = new Intent(MainActivity4.this, ChatListActivity.class);
                    startActivity(intent);
                })
                .setPositiveButton("Close", null)
                .show();
    }
}
