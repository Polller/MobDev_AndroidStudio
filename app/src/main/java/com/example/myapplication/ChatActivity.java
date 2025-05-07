package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {

    private EditText inputMessage;
    private Button sendButton;
    private LinearLayout chatContainer;
    private ScrollView chatScroll;
    private String chatName;
    private ArrayList<String> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        inputMessage = findViewById(R.id.inputMessage);
        sendButton = findViewById(R.id.sendButton);
        chatContainer = findViewById(R.id.chatContainer);
        chatScroll = findViewById(R.id.chatScroll);

        chatName = getIntent().getStringExtra("chatName");
        messageList = new ArrayList<>();

        loadMessages();

        sendButton.setOnClickListener(v -> {
            String message = inputMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                addMessageToChat(message, true);
                messageList.add("user:" + message);
                inputMessage.setText("");

                chatContainer.postDelayed(() -> {
                    String reply = "Echo - " + message;
                    addMessageToChat(reply, false);
                    messageList.add("bot:" + reply);
                    saveMessages();
                }, 500);

                saveMessages();
            }
        });
    }

    private void addMessageToChat(String message, boolean isUser) {
        View messageView = LayoutInflater.from(this).inflate(R.layout.chat_bubble, chatContainer, false);
        TextView textView = messageView.findViewById(R.id.bubbleText);
        textView.setText(message);

        LinearLayout bubbleLayout = messageView.findViewById(R.id.bubbleLayout);
        bubbleLayout.setGravity(isUser ? Gravity.END : Gravity.START);
        textView.setBackgroundResource(isUser ? R.drawable.bubble_user : R.drawable.bubble_bot);

        chatContainer.addView(messageView);
        chatScroll.post(() -> chatScroll.fullScroll(View.FOCUS_DOWN));
    }

    private void saveMessages() {
        SharedPreferences prefs = getSharedPreferences("chat_messages", MODE_PRIVATE);
        StringBuilder sb = new StringBuilder();
        for (String msg : messageList) {
            sb.append(msg).append(";;");
        }
        prefs.edit().putString(chatName, sb.toString()).apply();
    }

    private void loadMessages() {
        SharedPreferences prefs = getSharedPreferences("chat_messages", MODE_PRIVATE);
        String saved = prefs.getString(chatName, "");
        if (!saved.isEmpty()) {
            String[] parts = saved.split(";;");
            for (String part : parts) {
                if (part.startsWith("user:")) {
                    String msg = part.substring(5);
                    addMessageToChat(msg, true);
                    messageList.add(part);
                } else if (part.startsWith("bot:")) {
                    String msg = part.substring(4);
                    addMessageToChat(msg, false);
                    messageList.add(part);
                }
            }
        }
    }
}
