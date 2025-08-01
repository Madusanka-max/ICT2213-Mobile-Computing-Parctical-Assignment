package com.example.madusanka;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConstraintActivity extends AppCompatActivity {
    private EditText editTextStudentName, editTextEmail, editTextPhone;
    private Button submitButton, clearButton, viewDataButton;
    private StudentDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_constraint);

        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        submitButton = findViewById(R.id.submitButton);
        clearButton = findViewById(R.id.clearButton);
        viewDataButton = findViewById(R.id.viewDataButton);

        dbHelper = new StudentDatabaseHelper(this);

        submitButton.setOnClickListener(v -> {
            String name = editTextStudentName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();

            if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
                boolean success = dbHelper.saveStudent(name, email, phone);
                if (success) {
                    Toast.makeText(this, "◆ DATA UPLOADED TO QUANTUM STORAGE", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(this, "◆ QUANTUM ERROR: UPLOAD FAILED", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "◆ ERROR: INCOMPLETE DATA MATRIX", Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(v -> {
            clearFields();
            Toast.makeText(this, "◆ FIELDS RESET", Toast.LENGTH_SHORT).show();
        });

        viewDataButton.setOnClickListener(v -> showQuantumDatabase());

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

    private void showQuantumDatabase() {
        String data = dbHelper.getFormattedStudents();
        int count = dbHelper.getStudentCount();
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("◆ QUANTUM DATABASE [" + count + " RECORDS]");
        builder.setMessage(data);
        builder.setPositiveButton("◆ CLOSE", null);
        builder.setNegativeButton("◆ CLEAR ALL", (dialog, which) -> {
            boolean success = dbHelper.clearData();
            if (success) {
                Toast.makeText(this, "◆ QUANTUM DATABASE PURGED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "◆ ERROR: PURGE FAILED", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}