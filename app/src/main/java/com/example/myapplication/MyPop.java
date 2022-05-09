package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyPop extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypopup);
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();

        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        layoutParams.dimAmount= 0.1f;getWindow().setAttributes(layoutParams);


        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 0.25);
        int height = (int) (display.getHeight() * 0.15);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

    }

}
