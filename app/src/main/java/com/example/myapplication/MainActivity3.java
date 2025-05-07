package com.example.myapplication;

import android.app.AlertDialog;
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
            "Shadow Notes Band delivers poetic indie rock inspired by Arctic Monkeys and Radiohead. Their emotional lyrics, combined with tight instrumentals, make them a perfect choice for intimate concerts or energetic stages. Hiring them guarantees a unique, heartfelt performance.",
            "Echo Pulse brings the excitement of synth pop with a modern twist, drawing inspiration from CHVRCHES and Owl City. Their upbeat rhythms and electronic vibes make them ideal for lively events and youth-oriented festivals.",
            "Neon Rhythms fuses dance rock with electronic elements, reminiscent of Daft Punk and The 1975. Known for their high-energy sets, they’re a great choice for events where you want the crowd dancing all night.",
            "The Bass Breakers are a funk rock powerhouse with influences from Red Hot Chili Peppers and Bootsy Collins. Their groovy rhythms and stage presence are sure to hype up any audience and bring people to the dance floor.",
            "Crimson Sound offers a powerful alternative metal experience, echoing the styles of Linkin Park and Evanescence. With heavy riffs and emotional vocals, they’re perfect for dramatic, energetic performances.",
            "Funky Strums blends soul and funk, channeling James Brown and Stevie Wonder in every performance. Their brass section and vintage vibe will light up any venue with retro charm and irresistible rhythm.",
            "Golden Mic delivers acoustic pop filled with storytelling and smooth melodies, influenced by Ed Sheeran and Colbie Caillat. Their heartfelt performances are perfect for romantic, relaxed settings like weddings or cafes.",
            "Silent Amp creates ambient rock soundscapes inspired by Sigur Rós and Explosions in the Sky. Their dreamy, instrumental-driven performances make them ideal for artistic events and peaceful atmospheres."
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

        TextView descriptionView = new TextView(this);
        descriptionView.setText(description);
        descriptionView.setPadding(30, 30, 30, 30);
        descriptionView.setTextSize(16f);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(descriptionView);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(scrollView);

        Button messageButton = new Button(this);
        messageButton.setText("Message!");
        messageButton.setOnClickListener(v -> {
            builder.setCancelable(true);
            builder.create().dismiss();
        });

        layout.addView(messageButton);
        layout.setPadding(30, 30, 30, 30);

        builder.setView(layout);
        builder.setNegativeButton("Close", null);
        builder.show();
    }
}