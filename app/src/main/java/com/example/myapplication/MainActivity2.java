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
    private LinearLayout footerLogsLayout;
    private LinearLayout footerChatLayout;
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
        footerLogsLayout = findViewById(R.id.footerLogsLayout);
        footerChatLayout = findViewById(R.id.footerChatLayout);

        topHiringBandLayout.setOnClickListener(v -> startActivity(new Intent(this, MainActivity3.class)));
        genreTypeLayout.setOnClickListener(v -> startActivity(new Intent(this, MainActivity4.class)));
        musicArtistTypeLayout.setOnClickListener(v -> startActivity(new Intent(this, MainActivity5.class)));
        musicGenerationTypeLayout.setOnClickListener(v -> startActivity(new Intent(this, MainActivity6.class)));
        applyBandMemberLayout.setOnClickListener(v -> startActivity(new Intent(this, MainActivity8.class)));
        footerLogsLayout.setOnClickListener(v -> startActivity(new Intent(this, MainActivity9.class)));

        footerChatLayout.setOnClickListener(v -> startActivity(new Intent(this, ChatListActivity.class))); // NAVIGATE TO CHATLIST

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItems(s.toString().toLowerCase());
            }
            @Override public void afterTextChanged(Editable s) {}
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
