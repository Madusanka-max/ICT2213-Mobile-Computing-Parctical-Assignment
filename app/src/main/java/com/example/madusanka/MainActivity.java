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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnItemSelectedListener(item -> {
            Intent intent;
            String name = editTextName.getText().toString();
            
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