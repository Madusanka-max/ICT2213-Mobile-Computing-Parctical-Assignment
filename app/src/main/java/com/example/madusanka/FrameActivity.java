package com.example.madusanka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FrameActivity extends AppCompatActivity {
    private TextView welcomeText1;
    private TextView welcomeText2;
    private Button editProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_frame);

        welcomeText1 = findViewById(R.id.welcomeText1);
        welcomeText2 = findViewById(R.id.welcomeText1);
        editProfileButton = findViewById(R.id.editProfileButton);

        String userName = getIntent().getStringExtra("USER_NAME");
        if (userName != null && !userName.isEmpty()) {
            welcomeText1.setText("Welcome");
            welcomeText2.setText( ""+ userName);
        }

        editProfileButton.setOnClickListener(v -> 
            Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
        );

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(FrameActivity.this, MainActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_frame);
        bottomNavigation.setOnItemSelectedListener(item -> {
            Intent intent;
            if (item.getItemId() == R.id.nav_frame) {
                return true;
            } else if (item.getItemId() == R.id.nav_constraint) {
                intent = new Intent(FrameActivity.this, ConstraintActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_relative) {
                intent = new Intent(FrameActivity.this, RelativeActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}