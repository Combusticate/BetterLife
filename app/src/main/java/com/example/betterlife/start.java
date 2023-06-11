package com.example.betterlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class start extends AppCompatActivity {

    MaterialButton sreg, slog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sreg = findViewById(R.id.startregbtn);
        slog = findViewById(R.id.startlogbtn);

        sreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(start.this, Register.class);
                startActivity(intent);
            }
        });

        slog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(start.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}