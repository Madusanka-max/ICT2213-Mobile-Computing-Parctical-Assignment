package com.example.madusanka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName, editTextAge;
    private BottomNavigationView bottomNavigation;
    private StudentDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        dbHelper = new StudentDatabaseHelper(this);

        bottomNavigation.setOnItemSelectedListener(item -> {
            Intent intent;
            String name = editTextName.getText().toString().trim();
            String age = editTextAge.getText().toString().trim();
            
            // Save user data if both fields are filled
            if (!name.isEmpty() && !age.isEmpty()) {
                dbHelper.saveUser(name, age);
            }
            
            if (item.getItemId() == R.id.nav_frame) {
                intent = new Intent(MainActivity.this, FrameActivity.class);
                intent.putExtra("USER_NAME", name);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_constraint) {
                intent = new Intent(MainActivity.this, ConstraintActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_relative) {
                intent = new Intent(MainActivity.this, RelativeActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}