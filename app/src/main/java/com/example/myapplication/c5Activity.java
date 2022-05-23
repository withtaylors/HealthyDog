package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class c5Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c5);
        TextView result = findViewById(R.id.result);
        TextView confidences = findViewById(R.id.confidence);

        String sub_result, sub_confidences ;

        Intent intent = getIntent();
        sub_result = intent.getStringExtra("result");
        sub_confidences = intent.getStringExtra("confidences");

        result.setText(sub_result);
        confidences.setText(sub_confidences);

    }
}