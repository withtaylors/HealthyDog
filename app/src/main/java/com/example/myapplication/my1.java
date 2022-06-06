package com.example.myapplication;

import static com.example.myapplication.R.id;
import static com.example.myapplication.R.layout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class my1 extends AppCompatActivity {
        ArrayAdapter<CharSequence> adspin1, adspin2, adspin3, adspin4, adspin5;
        Button Female_1;
        Button Male_1;
        private boolean check = false;
        private static final int PICK_IMAGE_REQUEST = 1;
        String imgString;


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
                //스피너 값 선택하기

                final Spinner spin1 = (Spinner) findViewById(id.spinner_year1);
                final Spinner spin2 = (Spinner) findViewById(id.spinner_month1);
                final Spinner spin3 = (Spinner) findViewById(id.spinner_day1);
                final Spinner spin4 = (Spinner) findViewById(id.spinner_type1);
                final Spinner spin5 = (Spinner) findViewById(id.spinner_gender1);

                adspin1 = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_dropdown_item);
                adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin1.setAdapter(adspin1);
                spin1.setSelection(0);

                adspin2 = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_dropdown_item);
                adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin2.setAdapter(adspin2);
                spin2.setSelection(0);

                adspin3 = ArrayAdapter.createFromResource(this, R.array.day, android.R.layout.simple_spinner_dropdown_item);
                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin3.setAdapter(adspin3);
                spin3.setSelection(0);

                adspin4 = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_dropdown_item);
                adspin4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin4.setAdapter(adspin4);
                spin4.setSelection(0);

                adspin5 = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_dropdown_item);
                adspin5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin5.setAdapter(adspin5);
                spin5.setSelection(0);

                //여기서부터 인텐트

                final EditText inputname = findViewById(id.inputname);
                final EditText walk_1 = findViewById(id.walk_1);
                final ImageView profile1 = findViewById(id.profile1);



                Button submit_1 = (Button) findViewById(id.submit_1);



                submit_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                int time = Integer.parseInt(walk_1.getText().toString());
                                String name = inputname.getText().toString().trim();

                                //imagview bitmap으로 바꾼 후 string 으로 보내기
                                BitmapDrawable drawable = (BitmapDrawable)profile1.getDrawable();
                                Bitmap bitmap1 = drawable.getBitmap();
                                profile1.setImageBitmap(bitmap1);
                                ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                                bitmap1.compress(Bitmap.CompressFormat.PNG, 70, baos1);
                                byte[]bytes1 = baos1.toByteArray();
                                String temp1 = Base64.encodeToString(bytes1, Base64.DEFAULT);


                                SharedPreferences sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name", name);
                                editor.putString("profile", temp1);


                                editor.putInt("time", time);
                                editor.putString("year", spin1.getSelectedItem().toString());
                                editor.putString("month", spin2.getSelectedItem().toString());
                                editor.putString("day", spin3.getSelectedItem().toString());
                                editor.putString("type", spin4.getSelectedItem().toString());

                                editor.apply();


                                int list = 1; //어디 페이지로 프레그먼트 시작할지 정하는 변수
                                Intent intent = new Intent(my1.this, c1Activity.class);
                                intent.putExtra("list", list);
                                startActivity(intent);
                                //야매로 c1은 액티비티니까 액티비티로 이동
                                //대신 시작할 때 마이페이지 뜰 수 있게 list = 1로 변경

                        }
                });


                //클릭 시 인텐트 부분 끝

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

                ImageButton profile_pic1 = (ImageButton) findViewById(id.profile_pic1);

                profile_pic1.setOnClickListener(new View.OnClickListener() {
                        @Override //갤러리 부분
                        public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "select Picture"), PICK_IMAGE_REQUEST);
                        }
                });
        }

        @Override //사진 띄우기
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                        Uri uri = data.getData();

                        try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                ImageView profile1 = (ImageView) findViewById(id.profile1);
                                profile1.setImageBitmap(bitmap);





                        } catch (IOException e) {
                                e.printStackTrace();
                        }

                } else if (resultCode == RESULT_CANCELED) {
                        Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();

                }


        }



}