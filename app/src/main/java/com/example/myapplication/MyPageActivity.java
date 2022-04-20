package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View;

public class MyPageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        final Spinner spin1 = (Spinner)findViewById(R.id.spinner_year1);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinner_month1);
        final Spinner spin3 = (Spinner)findViewById(R.id.spinner_day1);
        final Spinner spin4 = (Spinner)findViewById(R.id.spinner_type1);
        final Spinner spin5 = (Spinner)findViewById(R.id.spinner_gender1);



    }
}