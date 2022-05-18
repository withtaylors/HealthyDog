package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class c5Activity extends AppCompatActivity {
    TextView result2, confidence2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c5);

        result2 = findViewById(R.id.result);
        confidence2 = findViewById(R.id.confidence);

        Intent myIntent = getIntent();
        String results = myIntent.getStringExtra("main_result");

    }


}