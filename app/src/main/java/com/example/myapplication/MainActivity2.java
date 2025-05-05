package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private LinearLayout topHiringBandLayout;
    private LinearLayout genreTypeLayout;
    private LinearLayout musicArtistTypeLayout;
    private LinearLayout musicGenerationTypeLayout;
    private LinearLayout applyBandMemberLayout;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        searchEditText = findViewById(R.id.searchEditText);
        topHiringBandLayout = findViewById(R.id.topHiringBandLayout);
        genreTypeLayout = findViewById(R.id.genreTypeLayout);
        musicArtistTypeLayout = findViewById(R.id.musicArtistTypeLayout);
        musicGenerationTypeLayout = findViewById(R.id.musicGenerationTypeLayout);
        applyBandMemberLayout = findViewById(R.id.applyBandMemberLayout);

        topHiringBandLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        });

        genreTypeLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
            startActivity(intent);
        });

        musicArtistTypeLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
            startActivity(intent);
        });

        musicGenerationTypeLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity6.class);
            startActivity(intent);
        });

        applyBandMemberLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity8.class);
            startActivity(intent);
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItems(s.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterItems(String query) {
        if (query.isEmpty()) {
            topHiringBandLayout.setVisibility(View.VISIBLE);
            genreTypeLayout.setVisibility(View.VISIBLE);
            musicArtistTypeLayout.setVisibility(View.VISIBLE);
            musicGenerationTypeLayout.setVisibility(View.VISIBLE);
            applyBandMemberLayout.setVisibility(View.VISIBLE);
        } else {
            topHiringBandLayout.setVisibility(matchesQuery(query, "top", "hiring", "band") ? View.VISIBLE : View.GONE);
            genreTypeLayout.setVisibility(matchesQuery(query, "genre") ? View.VISIBLE : View.GONE);
            musicArtistTypeLayout.setVisibility(matchesQuery(query, "artist", "music") ? View.VISIBLE : View.GONE);
            musicGenerationTypeLayout.setVisibility(matchesQuery(query, "generation", "music") ? View.VISIBLE : View.GONE);
            applyBandMemberLayout.setVisibility(matchesQuery(query, "apply", "member", "band") ? View.VISIBLE : View.GONE);
        }
    }

    private boolean matchesQuery(String query, String... keywords) {
        for (String keyword : keywords) {
            if (query.contains(keyword)) return true;
        }
        return false;
    }
}
