package com.example.myapplication;

import static com.example.myapplication.R.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View;

public class MyPageActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3, adspin4, adspin5;

    ImageButton profile_pic1 = (ImageButton) findViewById(id.profile_pic1);
    final int GET_GALLERY_IMAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i("Is on?", "Turning immersive mode mode off. ");
        } else {
            Log.i("Is on?", "Turning immersive mode mode on.");
        }
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);


        setContentView(layout.activity_my_page);
        final Spinner spin1 = (Spinner) findViewById(id.spinner_year1);
        final Spinner spin2 = (Spinner) findViewById(id.spinner_month1);
        final Spinner spin3 = (Spinner) findViewById(id.spinner_day1);
        final Spinner spin4 = (Spinner) findViewById(id.spinner_type1);
        final Spinner spin5 = (Spinner) findViewById(id.spinner_gender1);

        adspin1 = ArrayAdapter.createFromResource(this, array.year, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);

        adspin2 = ArrayAdapter.createFromResource(this, array.month, android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adspin2);

        adspin3 = ArrayAdapter.createFromResource(this, array.day, android.R.layout.simple_spinner_dropdown_item);
        adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(adspin3);

        adspin4 = ArrayAdapter.createFromResource(this, array.type, android.R.layout.simple_spinner_dropdown_item);
        adspin4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin4.setAdapter(adspin4);

        adspin5 = ArrayAdapter.createFromResource(this, array.gender, android.R.layout.simple_spinner_dropdown_item);
        adspin5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin5.setAdapter(adspin5);


    }



}