package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button signInButton, registerButton;
    ImageView facebookIcon, googleIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        registerButton = findViewById(R.id.registerButton);
        facebookIcon = findViewById(R.id.facebookIcon);
        googleIcon = findViewById(R.id.googleIcon);

        signInButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String registeredEmail = prefs.getString("email", "");
            String registeredPassword = prefs.getString("password", "");

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            } else if (!email.equals(registeredEmail) || !password.equals(registeredPassword)) {
                Toast.makeText(this, "Invalid credentials or user not registered", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Logging in as " + email, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                finish();
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        facebookIcon.setOnClickListener(v -> {
            Intent fbIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://m.facebook.com/login.php/"));
            startActivity(fbIntent);
        });

        googleIcon.setOnClickListener(v -> {
            Intent googleIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://accounts.google.com/signin"));
            startActivity(googleIntent);
        });
    }
}
