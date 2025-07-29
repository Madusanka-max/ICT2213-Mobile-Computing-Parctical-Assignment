package com.example.madusanka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FrameActivity extends AppCompatActivity {
    private ImageView profileImage;
    private TextView welcomeText;
    private Button editProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        profileImage = findViewById(R.id.profileImage);
        welcomeText = findViewById(R.id.welcomeText);
        editProfileButton = findViewById(R.id.editProfileButton);

        String userName = getIntent().getStringExtra("USER_NAME");
        if (userName != null && !userName.isEmpty()) {
            welcomeText.setText("Welcome, " + userName + "!");
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