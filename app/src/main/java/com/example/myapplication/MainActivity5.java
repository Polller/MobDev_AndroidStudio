package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {

    String[] artists = {
            "Vinzem Acabo",
            "Wawa Luardo",
            "Paulo Seares",
            "Stephen Alde Seares",
            "Kenny Ken de Lara",
            "Michael Catubig",
            "Yves Mayol",
            "Kent Recede"
    };

    String[] descriptions = {
            "🎤 Favorite Artists: Adele, Ariana Grande\n🎶 Genre: Pop Ballads\n🌟 Why Hire: Vinzem has a soulful voice and stage charisma that captivates audiences.",
            "🎤 Favorite Artists: Bruno Mars, The Weeknd\n🎶 Genre: R&B, Funk\n🌟 Why Hire: Wawa brings smooth vocals and dynamic rhythm that energizes any crowd.",
            "🎤 Favorite Artists: Billie Eilish, Halsey\n🎶 Genre: Indie Pop\n🌟 Why Hire: Paulo delivers emotional depth with a unique tone and lyrical expression.",
            "🎤 Favorite Artists: Ed Sheeran, James Bay\n🎶 Genre: Acoustic, Folk\n🌟 Why Hire: Stephen's raw vocals and acoustic talent create intimate performances.",
            "🎤 Favorite Artists: Taylor Swift, Olivia Rodrigo\n🎶 Genre: Pop Rock\n🌟 Why Hire: Kenny connects with the youth through powerful storytelling and vocals.",
            "🎤 Favorite Artists: Imagine Dragons, Linkin Park\n🎶 Genre: Alternative Rock\n🌟 Why Hire: Michael's energetic voice fits modern rock and live performances.",
            "🎤 Favorite Artists: Daft Punk, Zedd\n🎶 Genre: Electronic, Synthwave\n🌟 Why Hire: Yves excels in digital soundscapes with rich vocal layering.",
            "🎤 Favorite Artists: Sia, Florence Welch\n🎶 Genre: Indie, Art Pop\n🌟 Why Hire: Kent offers ethereal and strong vocals that blend art and sound."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_artist_type);

        ListView artistListView = findViewById(R.id.artistListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artists);
        artistListView.setAdapter(adapter);

        artistListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedArtist = artists[position];
            String description = descriptions[position];
            showArtistDescriptionDialog(selectedArtist, description);
        });
    }

    private void showArtistDescriptionDialog(String artistName, String description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(artistName);

        TextView descriptionView = new TextView(this);
        descriptionView.setText(description);
        descriptionView.setPadding(20, 20, 20, 20);
        descriptionView.setTextSize(16f);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(descriptionView);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(scrollView);

        Button hireButton = new Button(this);
        hireButton.setText("Hire Vocalist");
        hireButton.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("chat_prefs", MODE_PRIVATE);
            String existingChats = prefs.getString("chat_list", "");
            if (!existingChats.contains(artistName)) {
                existingChats += artistName + ";";
                prefs.edit().putString("chat_list", existingChats).apply();
            }

            Intent intent = new Intent(MainActivity5.this, ChatListActivity.class);
            startActivity(intent);
        });

        layout.addView(hireButton);
        layout.setPadding(30, 30, 30, 30);

        builder.setView(layout);
        builder.setNegativeButton("Close", null);
        builder.show();
    }
}
