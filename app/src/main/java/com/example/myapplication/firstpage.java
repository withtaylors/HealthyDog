package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class firstpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage); //xml , java 소스 연결

        Handler handler = new Handler();

        //이름이 저장되어 있지 않다면, 액티비티 1로 이동.
        SharedPreferences sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", " ");
        if (name == " ") {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(firstpage.this, "처음오셨군요?\n반려동물 정보를 입력해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL,0,-500);
                    ViewGroup group = (ViewGroup)toast.getView();
                    TextView TextView = (TextView)group.getChildAt(0);
                    TextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(), my1.class);
                    startActivity(intent); //인트로 실행 후 바로 my1Activity로 넘어감.
                    finish();
                }
            }, 1500); //2초 후 인트로 실행
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), c1Activity.class);
                    startActivity(intent); //인트로 실행 후 바로 c1Activity로 넘어감.
                    finish();
                }
            }, 1500); //2초 후 인트로 실행
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}