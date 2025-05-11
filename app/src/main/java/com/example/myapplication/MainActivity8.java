package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_band_member);

        AutoCompleteTextView genreDropdown = findViewById(R.id.genreDropdown);
        AutoCompleteTextView instrumentDropdown = findViewById(R.id.instrumentDropdown);
        TextView bandInfoText = findViewById(R.id.bandInfoText);
        EditText nameField = findViewById(R.id.editTextText2);
        EditText addressField = findViewById(R.id.editTextAddress);
        EditText phoneField = findViewById(R.id.editTextPhone);
        Button applyButton = findViewById(R.id.button);

        String[] genres = {"Pop", "Rock", "Hip-Hop", "Jazz", "Classical", "Electronic", "Country", "Reggae", "Blues"};
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, genres);
        genreDropdown.setAdapter(genreAdapter);
        genreDropdown.setOnClickListener(v -> genreDropdown.showDropDown());

        String[] instruments = {"Guitar", "Drums", "Piano", "Violin", "Bass", "Saxophone", "Flute", "Trumpet", "Microphone"};
        ArrayAdapter<String> instrumentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, instruments);
        instrumentDropdown.setAdapter(instrumentAdapter);
        instrumentDropdown.setOnClickListener(v -> instrumentDropdown.showDropDown());

        String[][] bands = {
                {"Vinzem Band", "Rock"},
                {"Echo Beats", "Electronic"},
                {"Jazzminds", "Jazz"},
                {"The Harmonics", "Classical"},
                {"Rhythm Riot", "Hip-Hop"}
        };
        String[] bandNames = new String[bands.length];
        for (int i = 0; i < bands.length; i++) {
            bandNames[i] = bands[i][0];
        }
        bandInfoText.setOnClickListener(v -> {
            new AlertDialog.Builder(MainActivity8.this)
                    .setTitle("Select a Band")
                    .setItems(bandNames, (dialog, which) -> {
                        String selectedBand = bands[which][0];
                        String genre = bands[which][1];
                        bandInfoText.setText(selectedBand + " - Genre: " + genre);
                    })
                    .show();
        });

        // Load saved form data
        SharedPreferences savedPrefs = getSharedPreferences("application_data", MODE_PRIVATE);
        nameField.setText(savedPrefs.getString("name", ""));
        addressField.setText(savedPrefs.getString("address", ""));
        phoneField.setText(savedPrefs.getString("phone", ""));
        genreDropdown.setText(savedPrefs.getString("genre", ""));
        bandInfoText.setText(savedPrefs.getString("bandInfo", "Find a Band"));

        // Apply button logic
        applyButton.setOnClickListener(v -> {
            String name = nameField.getText().toString().trim();
            String address = addressField.getText().toString().trim();
            String phone = phoneField.getText().toString().trim();
            String genre = genreDropdown.getText().toString().trim();
            String bandInfo = bandInfoText.getText().toString().trim();
            String bookedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            String hiredOn = bookedDate;

            if (name.isEmpty() || genre.isEmpty() || bandInfo.startsWith("Find a Band")) {
                new AlertDialog.Builder(MainActivity8.this)
                        .setTitle("Missing Info")
                        .setMessage("Please fill out all fields before applying.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            savedPrefs.edit()
                    .putString("name", name)
                    .putString("address", address)
                    .putString("phone", phone)
                    .putString("genre", genre)
                    .putString("bandInfo", bandInfo)
                    .apply();

            String logEntry =
                    "🎤 Name: " + name + " (" + bandInfo + ")\n" +
                            "🎼 Genre: " + genre + "\n" +
                            "📅 Hired On: " + hiredOn + " at 9:00 AM\n" +
                            "📌 Booking Date: " + bookedDate;

            SharedPreferences logPrefs = getSharedPreferences("log_data", MODE_PRIVATE);
            String existingLogs = logPrefs.getString("logs", "");
            String updatedLogs = logEntry + "||" + existingLogs;
            logPrefs.edit().putString("logs", updatedLogs).apply();

            nameField.setText("");
            addressField.setText("");
            phoneField.setText("");
            genreDropdown.setText("");
            instrumentDropdown.setText("");
            bandInfoText.setText("Find a Band");

            savedPrefs.edit().clear().apply();

            Intent intent = new Intent(MainActivity8.this, MainActivity9.class);
            startActivity(intent);

        });
    }
}
