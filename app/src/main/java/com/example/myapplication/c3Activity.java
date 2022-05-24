package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;
import android.widget.TextView;

public class c3Activity extends AppCompatActivity {

    ImageButton btn2_change;
    ImageButton btn3_change;
    private boolean check2;
    private boolean check3;

    private ImageButton nav_camera;
    private ImageButton nav_pic_paws;
    private ImageButton nav_mypage;
    private ImageButton guidebtn;
//    private TextView text1;
//    private TextView text2;
//    private TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c3);

        nav_camera = (ImageButton) findViewById(R.id.nav_camera);
        nav_pic_paws = (ImageButton) findViewById(R.id.nav_pic_paws);
        nav_mypage = (ImageButton) findViewById(R.id.nav_mypage);
        guidebtn = findViewById(R.id.imageButton3);

//        text1 = findViewById(R.id.text1);
//        text2 = findViewById(R.id.text2);
//        text3 = findViewById(R.id.text3);
//


        btn2_change = (ImageButton)findViewById(R.id.choose_eye_left);
        btn2_change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check2 == false){
                    check2 = true;
                    btn2_change.setSelected(true);
                }else {
                    check2 = false;
                    btn2_change.setSelected(false);
                }
            }
        });

        btn3_change = (ImageButton)findViewById(R.id.choose_eye_right);
        btn3_change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check3 == false){
                    check3 = true;
                    btn3_change.setSelected(true);
                }else {
                    check3 = false;
                    btn3_change.setSelected(false);
                }
            }
        });
    }
//
//    public void onClick(View view){
//        switch(view.getId()){
//            case R.id.guidebtn:
//                Intent intent = new Intent(this, GuideActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.nav_camera:
//            case R.id.text1:
//                Intent intent2 = new Intent(this, c1Activity.class);
//                startActivity(intent2);
//                break;
//            case R.id.nav_pic_paws:
//            case R.id.text2:
//                Intent intent3 = new Intent(this, c3Activity.class);
//                startActivity(intent3);
//                break;
//            case R.id.nav_mypage:
//            case R.id.text3:
//                Intent intent4 = new Intent(this, my1.class);
//                startActivity(intent4);
//                break;
//        }
//        finish();
//    }
}

