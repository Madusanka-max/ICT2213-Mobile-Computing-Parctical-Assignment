package com.example.madusanka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConstraintActivity extends AppCompatActivity {
    private EditText editTextStudentName, editTextEmail, editTextPhone;
    private Button submitButton, clearButton;
    private StudentDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);

        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        submitButton = findViewById(R.id.submitButton);
        clearButton = findViewById(R.id.clearButton);

        dbHelper = new StudentDatabaseHelper(this);

        submitButton.setOnClickListener(v -> {
            String name = editTextStudentName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();

            if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
                long result = dbHelper.insertStudent(name, email, phone);
                if (result != -1) {
                    Toast.makeText(this, "Student saved successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(this, "Error saving student", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(v -> clearFields());

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ConstraintActivity.this, MainActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_constraint);
        bottomNavigation.setOnItemSelectedListener(item -> {
            Intent intent;
            if (item.getItemId() == R.id.nav_frame) {
                intent = new Intent(ConstraintActivity.this, FrameActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_constraint) {
                return true;
            } else if (item.getItemId() == R.id.nav_relative) {
                intent = new Intent(ConstraintActivity.this, RelativeActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void clearFields() {
        editTextStudentName.setText("");
        editTextEmail.setText("");
        editTextPhone.setText("");
    }
}