package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private LinearLayout topHiringBandLayout;
    private LinearLayout genreTypeLayout;
    private LinearLayout musicArtistTypeLayout;
    private LinearLayout musicGenerationTypeLayout;
    private LinearLayout hireBandMemberLayout;
    private LinearLayout applyBandMemberLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        topHiringBandLayout = findViewById(R.id.topHiringBandLayout);
        genreTypeLayout = findViewById(R.id.genreTypeLayout);
        musicArtistTypeLayout = findViewById(R.id.musicArtistTypeLayout);
        musicGenerationTypeLayout = findViewById(R.id.musicGenerationTypeLayout);
        hireBandMemberLayout = findViewById(R.id.hireBandMemberLayout);
        applyBandMemberLayout = findViewById(R.id.applyBandMemberLayout);

        topHiringBandLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        genreTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        musicArtistTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
                startActivity(intent);
            }
        });

        musicGenerationTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity6.class);
                startActivity(intent);
            }
        });

        hireBandMemberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity7.class);
                startActivity(intent);
            }
        });

        applyBandMemberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity8.class);
                startActivity(intent);
            }
        });
    }
}
