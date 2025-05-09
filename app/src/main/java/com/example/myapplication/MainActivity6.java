package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_generation_type);

        setupGenerationClick(R.id.textViewGen90s, "90s");
        setupGenerationClick(R.id.textViewGen80s, "80s");
        setupGenerationClick(R.id.textViewGen20s, "20s");
        setupGenerationClick(R.id.textViewGen70s, "70s");
        setupGenerationClick(R.id.textViewGen60s, "60s");
    }

    private void setupGenerationClick(int textViewId, final String generation) {
        TextView generationTextView = findViewById(textViewId);
        generationTextView.setOnClickListener(view -> showBandsDialog(generation));
    }

    private void showBandsDialog(String generation) {
        String[] bands;
        switch (generation) {
            case "90s":
                bands = new String[]{"Band 1: Nirvana", "Band 2: Backstreet Boys", "Band 3: Tupac"};
                break;
            case "80s":
                bands = new String[]{"Band 1: Queen", "Band 2: Michael Jackson", "Band 3: U2"};
                break;
            case "20s":
                bands = new String[]{"Band 1: BTS", "Band 2: Billie Eilish", "Band 3: Dua Lipa"};
                break;
            case "70s":
                bands = new String[]{"Band 1: ABBA", "Band 2: Led Zeppelin", "Band 3: Bee Gees"};
                break;
            case "60s":
                bands = new String[]{"Band 1: The Beatles", "Band 2: The Rolling Stones", "Band 3: The Doors"};
                break;
            default:
                bands = new String[]{"No bands available"};
        }

        new AlertDialog.Builder(this)
                .setTitle("Bands from the " + generation)
                .setItems(bands, (dialog, which) -> {
                    String selectedBand = bands[which];

                    SharedPreferences prefs = getSharedPreferences("chat_prefs", MODE_PRIVATE);
                    String existingChats = prefs.getString("chat_list", "");
                    if (!existingChats.contains(selectedBand)) {
                        existingChats += selectedBand + ";";
                        prefs.edit().putString("chat_list", existingChats).apply();
                    }

                    Intent intent = new Intent(MainActivity6.this, ChatListActivity.class);
                    startActivity(intent);
                })
                .setPositiveButton("Close", null)
                .show();
    }
}
