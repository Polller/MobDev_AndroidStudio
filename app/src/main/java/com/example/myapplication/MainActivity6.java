package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_generation_type);

        // Genre TextView (Hip Hop)
        TextView textViewGenre2 = findViewById(R.id.textViewGenre2);
        textViewGenre2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBandsDialog("Hip Hop");
            }
        });

        // Genre TextView (Classical)
        TextView textViewGenre3 = findViewById(R.id.textViewGenre3);
        textViewGenre3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBandsDialog("Classical");
            }
        });

        // Genre TextView (Rock)
        TextView textViewGenre4 = findViewById(R.id.textViewGenre4);
        textViewGenre4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBandsDialog("Rock");
            }
        });

        // Genre TextView (Jazz)
        TextView textViewGenre5 = findViewById(R.id.textViewGenre5);
        textViewGenre5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBandsDialog("Jazz");
            }
        });

        // Genre TextView (Pop)
        TextView textViewGenre6 = findViewById(R.id.textViewGenre6);
        textViewGenre6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBandsDialog("Pop");
            }
        });

        // Genre TextView (EDM)
        TextView textViewGenre7 = findViewById(R.id.textViewGenre7);
        textViewGenre7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBandsDialog("EDM");
            }
        });

        // Genre TextView (Country)
        TextView textViewGenre8 = findViewById(R.id.textViewGenre8);
        textViewGenre8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBandsDialog("Country");
            }
        });
    }

    private void showBandsDialog(String genre) {
        // Define the bands for each genre
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
                break;
        }

        // Create and show an AlertDialog with the list of bands
        new AlertDialog.Builder(this)
                .setTitle("Bands for " + genre)
                .setItems(bands, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle band selection here
                    }
                })
                .setPositiveButton("Close", null)
                .show();
    }
}