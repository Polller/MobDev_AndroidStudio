package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    private ListView chatListView;
    private ArrayList<String> chatList;

    private int getImageForName(String name) {
        switch (name) {
            case "Shadow Notes Band": return R.drawable.shadow_notes;
            case "Echo Pulse": return R.drawable.echo_pulse;
            case "Neon Rhythms": return R.drawable.neon_rhythms;
            case "The Bass Breakers": return R.drawable.bass_breakers;
            case "Crimson Sound": return R.drawable.crimson_sound;
            case "Funky Strums": return R.drawable.funky_strums;
            case "Golden Mic": return R.drawable.golden_mic;
            case "Silent Amp": return R.drawable.silent_amp;
            default: return R.drawable.account;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        chatListView = findViewById(R.id.chatListView);

        chatList = new ArrayList<>();

        SharedPreferences prefs = getSharedPreferences("chat_prefs", MODE_PRIVATE);
        String savedChats = prefs.getString("chat_list", "");

        if (!savedChats.isEmpty()) {
            String[] hiredBands = savedChats.split(";");
            for (String band : hiredBands) {
                if (!chatList.contains(band)) {
                    chatList.add(0, band); // Add new bands at the top
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.activity_list_item,
                android.R.id.text1,
                chatList
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                ImageView imageView = view.findViewById(android.R.id.icon);

                textView.setText(chatList.get(position));
                textView.setTextSize(18f);

                imageView.setImageResource(getImageForName(chatList.get(position)));
                imageView.getLayoutParams().width = 100;
                imageView.getLayoutParams().height = 100;
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return view;
            }
        };

        chatListView.setAdapter(adapter);

        chatListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ChatListActivity.this, ChatActivity.class);
            intent.putExtra("chatName", chatList.get(position));
            startActivity(intent);
        });
    }
}
