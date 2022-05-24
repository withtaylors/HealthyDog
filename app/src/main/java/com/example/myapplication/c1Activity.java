package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class c1Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    Camera camera = new Camera();
    Care care = new Care();
    MyPage mypage = new MyPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,camera).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_camera:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,camera).commit();
                        return true;
                    case R.id.nav_pic_paws:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,care).commit();
                        return true;
                    case R.id.nav_mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mypage).commit();
                        return true;
                }
                return false;
            }
        });
    }
}