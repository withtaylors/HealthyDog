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

//    Camera camera;
//    Care care;
//    MyPage myPage;

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    //FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
//    private Camera Camera = new Camera();
//    private Care Care = new Care();
//    private MyPage MyPage = new MyPage();




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_c1);
//
//        camera = new Camera();
//        care = new Care();
//        myPage = new MyPage();
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, camera).commit();
//
//        NavigationBarView navigationBarView = findViewById(R.id.navigationView);
//        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch(item.getItemId()){
//                    case R.id.nav_camera:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, camera).commit();
//                        return true;
//                    case R.id.nav_pic_paws:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, care).commit();
//                        return true;
//                    case R.id.nav_mypage:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, myPage).commit();
//                        return true;
//                }
//                return false;
//            }
//        });




//        ScrollView guidescroll = findViewById(R.id.guidescroll);
//        ImageButton downbtn = findViewById(R.id.downbtn);
//
//        downbtn.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(guidescroll.getVisibility() == View.VISIBLE)
//                    guidescroll.setVisibility(View.INVISIBLE);
//                else
//                    guidescroll.setVisibility(View.VISIBLE);
//            }
//        }) ;

//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
//        // 첫 화면 지정
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.frame_layout, Camera).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                switch (item.getItemId()) {
//                    case R.id.nav_camera: {
//                        transaction.replace(R.id.frame_layout, Camera).commitAllowingStateLoss();
//                        break;
//                    }
//                    case R.id.nav_pic_paws: {
//                        transaction.replace(R.id.frame_layout, Care).commitAllowingStateLoss();
//                        break;
//                    }
//                    case R.id.nav_mypage: {
//                        transaction.replace(R.id.frame_layout, MyPage).commitAllowingStateLoss();
//                        break;
//                    }
//                }
//                return true;
//            }
//        });


//        private ActionBar toolbar;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_c1);
//
//            toolbar = getSupportActionBar();
//
//            BottomNavigationView navigation = findViewById(R.id.navigationView);
//            navigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);
//
//            toolbar.setTitle("Uno");
//            loadFragment(new Camera());
//        }
//
//        private BottomNavigationView.OnItemSelectedListener mOnNavigationItemSelectedListener
//                = new BottomNavigationView.OnItemSelectedListener() {
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.nav_camera:
//                        toolbar.setTitle("Uno");
//                        loadFragment(new Camera());
//                        return true;
//                    case R.id.nav_pic_paws:
//                        toolbar.setTitle("Dos");
//                        loadFragment(new Care());
//                        return true;
//                    case R.id.nav_mypage:
//                        toolbar.setTitle("Tres");
//                        loadFragment(new MyPage());
//                        return true;
//                }
//                return false;
//            }
//        };
//
//        private void loadFragment(Fragment fragment) {
//            // load fragment
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }

    //}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);

        BottomNavigationView bottomNav = findViewById(R.id.menu);
        bottomNav.setOnItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    new Camera()).commit();
        }
    }

    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_camera:
                            selectedFragment = new Camera();
                            break;
                        case R.id.nav_pic_paws:
                            selectedFragment = new Care();
                            break;
                        case R.id.nav_mypage:
                            selectedFragment = new MyPage();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,
                            selectedFragment).commit();

                    return true;
                }
            };


    public void onClick(View view){
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
    }
}