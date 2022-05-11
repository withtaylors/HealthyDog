package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;

public class c3Activity extends AppCompatActivity {

    ImageButton btn2_change;
    ImageButton btn3_change;
    private boolean check2;
    private boolean check3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c3);

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
}

