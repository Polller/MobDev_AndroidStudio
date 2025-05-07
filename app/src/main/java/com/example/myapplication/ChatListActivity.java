package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatListActivity extends AppCompatActivity {

    private ListView chatListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        chatListView = findViewById(R.id.chatListView);

        chatList = new ArrayList<>(Arrays.asList(
                "Oh! Kapogi", "Amber Chua", "Lovella Sacala", "Jan Besignal", "Sabang Castillon", "Hans Segundino"
        ));

        SharedPreferences prefs = getSharedPreferences("chat_prefs", MODE_PRIVATE);
        String savedChats = prefs.getString("chat_list", "");

        if (!savedChats.isEmpty()) {
            String[] hiredArtists = savedChats.split(";");
            for (String artist : hiredArtists) {
                if (!chatList.contains(artist)) {
                    chatList.add(0, artist);
                }
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chatList);
        chatListView.setAdapter(adapter);

        chatListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ChatListActivity.this, ChatActivity.class);
            intent.putExtra("chatName", chatList.get(position));
            startActivity(intent);
        });
    }
}
