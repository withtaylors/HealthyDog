package com.example.myapplication;

import static com.example.myapplication.R.array;
import static com.example.myapplication.R.id;
import static com.example.myapplication.R.layout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class my1 extends AppCompatActivity {


        ArrayAdapter<CharSequence> adspin1, adspin2, adspin3, adspin4, adspin5;
        Button Female_1;
        Button Male_1;
        private boolean check = false;
        ImageButton profile_pic1;
        ImageView profile1;
        private static final int PICK_IMAGE_REQUEST = 1;



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


                setContentView(layout.activity_my1);

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

                Female_1 = (Button) findViewById(id.Female_1);
                Female_1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                if (check == false) {
                                        check = true;
                                        Female_1.setSelected(true);
                                } else {
                                        check = false;
                                        Female_1.setSelected(false);
                                }
                        }
                });

                Male_1 = (Button) findViewById(id.Male_1);
                Male_1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                if (check == false) {
                                        check = true;
                                        Male_1.setSelected(true);
                                } else {
                                        check = false;
                                        Male_1.setSelected(false);
                                }
                        }
                });



                ImageButton profile_pic1 = (ImageButton)findViewById(id.profile_pic1);


                profile_pic1.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "select Picture"), PICK_IMAGE_REQUEST);
                        }
                });
        }
        @Override
        public  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


                super.onActivityResult(requestCode, resultCode, data);
                if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
                        Uri uri = data.getData();
                                try {
                                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                        ImageView profile1 = (ImageView) findViewById(id.profile_pic1);
                                        profile1.setImageBitmap(bitmap);





                                } catch (IOException e) {
                                        e.printStackTrace();
                                }

                }else if (resultCode == RESULT_CANCELED){
                                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();

                        }
                }
        }