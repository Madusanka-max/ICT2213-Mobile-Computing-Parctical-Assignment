package com.example.madusanka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RelativeActivity extends AppCompatActivity {
    private TextView dashboardText, notificationsText, accountStatusText;
    private EditText searchEditText;
    private Button viewProfileButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);

        dashboardText = findViewById(R.id.dashboardText);
        notificationsText = findViewById(R.id.notificationsText);
        accountStatusText = findViewById(R.id.accountStatusText);
        searchEditText = findViewById(R.id.searchEditText);
        viewProfileButton = findViewById(R.id.viewProfileButton);
        settingsButton = findViewById(R.id.settingsButton);

        viewProfileButton.setOnClickListener(v -> 
            Toast.makeText(this, "View Profile clicked", Toast.LENGTH_SHORT).show()
        );

        settingsButton.setOnClickListener(v -> 
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
        );

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(RelativeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_relative);
        bottomNavigation.setOnItemSelectedListener(item -> {
            Intent intent;
            if (item.getItemId() == R.id.nav_frame) {
                intent = new Intent(RelativeActivity.this, FrameActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_constraint) {
                intent = new Intent(RelativeActivity.this, ConstraintActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_relative) {
                return true;
            }
            return false;
        });
    }
}