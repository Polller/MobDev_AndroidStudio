package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_band_member);

        // Music Genre Dropdown
        AutoCompleteTextView genreDropdown = findViewById(R.id.genreDropdown);
        String[] genres = new String[] {
                "Pop", "Rock", "Hip-Hop", "Jazz", "Classical",
                "Electronic", "Country", "Reggae", "Blues"
        };
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                genres
        );
        genreDropdown.setAdapter(genreAdapter);
        genreDropdown.setOnClickListener(v -> genreDropdown.showDropDown());

        // Instrument Dropdown
        AutoCompleteTextView instrumentDropdown = findViewById(R.id.instrumentDropdown);
        String[] instruments = new String[] {
                "Guitar", "Drums", "Piano", "Violin", "Bass",
                "Saxophone", "Flute", "Trumpet", "Microphone"
        };
        ArrayAdapter<String> instrumentAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                instruments
        );
        instrumentDropdown.setAdapter(instrumentAdapter);
        instrumentDropdown.setOnClickListener(v -> instrumentDropdown.showDropDown());

        // Band Info TextView (Clickable)
        TextView bandInfoText = findViewById(R.id.bandInfoText);
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

        // Apply Button (Clear all fields and reset to default state)
        Button applyButton = findViewById(R.id.button);
        applyButton.setOnClickListener(v -> {
            // Clear all input fields
            genreDropdown.setText("");  // Clear genre dropdown
            instrumentDropdown.setText("");  // Clear instrument dropdown
            bandInfoText.setText("Vinzem Band: 97% hiring");  // Reset band info text

            // Clear the name field
            EditText nameField = findViewById(R.id.editTextText2); // Name field
            nameField.setText("");

            // Clear the address field (add this part)
            EditText addressField = findViewById(R.id.editTextAddress); // Address field
            addressField.setText("");
        });
    }
}
