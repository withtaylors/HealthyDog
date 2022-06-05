package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class c1Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    Camera camera = new Camera();
    Care care = new Care();
    MyPage mypage = new MyPage();

    int list ; //어디 페이지에서 시작할지 정하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //어디페이지에서 시작할지 정하는 변수 my1에서 받기
        Intent intent = getIntent();
        list = intent.getIntExtra("list",0);

        System.out.println("--변수 list의 값은 : "+list);

        if(list == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,camera).commit();
            System.out.println("--camera 페이지가 열립니다.");
        } else if (list == 1){
            getSupportFragmentManager().beginTransaction().detach(mypage).attach(mypage).commitNow(); //새로고침
            getSupportFragmentManager().beginTransaction().remove(mypage).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mypage).commit();
            list = 0;

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setSelectedItemId(R.id.nav_mypage);

            System.out.println("--mypage가 열립니다. --변수 list의 값은 :"+list);
        }

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