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
        String[]optionLavala=getResources().getStringArray(R.array.year);
        ArrayAdapter<String>adapter=new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item,optionLavala);
        Spinner obj=(Spinner)findViewById(R.id.spinner_year1);
        obj.setAdapter(adapter);
    }
}