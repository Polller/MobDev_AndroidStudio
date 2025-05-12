package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button signInButton;
    ImageView facebookIcon, googleIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        facebookIcon = findViewById(R.id.facebookIcon);
        googleIcon = findViewById(R.id.googleIcon);

        signInButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            } else if (!email.matches("^[\\w.-]+@(?:gmail\\.com|yahoo\\.com|hotmail\\.com)$")) {
                Toast.makeText(this, "Email must end with gmail.com, yahoo.com, or hotmail.com", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 8) {
                Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Logging in as " + email, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        facebookIcon.setOnClickListener(v -> {
            Intent fbIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://m.facebook.com/login.php/"));
            startActivity(fbIntent);
        });

        googleIcon.setOnClickListener(v -> {
            Intent googleIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://accounts.google.com/v3/signin/challenge/pwd?TL=AArrULTkGwP2-w3Ceq6Z7Y8ou3muTDjIc28gPePxvKdFvekYiDvHTCvTa_aZhRdI&cid=2&continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2Fspreauth&emr=1&flowName=GlifWebSignIn&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2Fspreauth&ifkv=ASKV5Mg9PCMjGwM8UHIqLTxXIUbWj7TF0Uf0jTS-13bGIG8-QT9KRhet5MLE-MA-L-JPPMHrmF8diw&osid=1&rart=ANgoxcfGR4y-5pVGtZoIsB3QSm-pCTluBSSPVXb0doNKodBF3xl2UZHIgfNsyVxUiZCs_uBlVzcdQlKDqEcxeGifZDAqPYaC63maxFJb-J1lxYWktSxCCHw&service=mail"));
            startActivity(googleIntent);
        });
    }
}
