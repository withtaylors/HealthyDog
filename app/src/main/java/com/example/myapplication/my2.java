package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class my2 extends AppCompatActivity {
    TextView name2;
    TextView walk_2;
    TextView birth_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);

        Intent receive_intent = getIntent();
        String str1;
        String str2;
        String str3;

        name2 = findViewById(R.id.name2);
        walk_2 = findViewById(R.id.walk_2);
        birth_2 = findViewById(R.id.birth_2);

        String name = receive_intent.getStringExtra("이름");
        String time = receive_intent.getStringExtra("시간");

        str1 = receive_intent.getExtras().getString("년도");
        str2 = receive_intent.getExtras().getString("월");
        str3 = receive_intent.getExtras().getString("일");

        birth_2.setText(str1+" . "+str2+" . "+str3);

        walk_2.setText(time);
        name2.setText(name);
    }
}