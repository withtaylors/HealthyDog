package com.example.myapplication;

import static android.graphics.BlendMode.COLOR;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.os.Bundle;

public class c3Activity extends Activity {

    ImageButton btn2_change;
    ImageButton btn3_change;
    private boolean check2;
    private boolean check3;
    Button choose_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_c3);

        Intent intent = new Intent(this, c4Activity.class);

//        Intent intent2 = new Intent(this, c4Activity.class);
//        Intent intent3 = new Intent(this, c4Activity.class);

        btn2_change = (ImageButton)findViewById(R.id.choose_eye_left);
        btn2_change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check2 == false){
                    check2 = true;
                    btn2_change.setSelected(true);
                    intent.putExtra("checkOn", true);
                }else {
                    check2 = false;
                    btn2_change.setSelected(false);
                    intent.putExtra("checkOn", false);
                }
                if (check2 == true || check3 == true ){
                    choose_button.setEnabled(true);
                }else{
                    choose_button.setEnabled(false);
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
                    intent.putExtra("checkOn_r", true);
                }else {
                    check3 = false;
                    btn3_change.setSelected(false);
                    intent.putExtra("checkOn_r", false);
                }
                if (check2 == true || check3 == true ){
                    choose_button.setEnabled(true);
                }else{
                    choose_button.setEnabled(false);
                }
            }
        });

        choose_button = (Button)findViewById(R.id.choose_button);

        choose_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });
    }
}