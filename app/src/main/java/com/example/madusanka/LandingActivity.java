package com.example.madusanka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);

        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(v -> {
            Intent intent = new Intent(LandingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}