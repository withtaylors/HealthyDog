package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ml.ModelT;

import org.json.JSONArray;
import org.json.JSONException;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class t4Activity extends AppCompatActivity {
    ImageButton t3camera;
    Button t3button;
    ImageView t3pic;
    int timageSize = 224;
    String tresult;
    String[] tclasses = {"치석이 있을 확률이 높다", "치석이 있을 확률이 낮다"};
    String tresult_info = "강아지에게 치아질병이 생기면 끝내 구강내 염증 삼출물과 세균은 혈관을 타고 심장, 간, 신장 등에 악영향을 미치게 됩니다. 정확한 진단을 위해 동물병원을 방문해주세요. ";


    Date currentTime = Calendar.getInstance().getTime(); //현재 시간 가져오기
    String simpleDateFormat = new SimpleDateFormat("MM.dd", Locale.getDefault()).format(currentTime); //시간 저장 할 데이터포멧

    String day_t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t4);

        t3camera = findViewById(R.id.t3camera);
        t3pic =findViewById(R.id.t3pic);
        t3button = findViewById(R.id.t3button);


        t3camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });




    }
    public void classifytimage(Bitmap image){


        SharedPreferences sharedPreferences = getSharedPreferences("t_total_result", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            ModelT model = ModelT.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer tbyteBuffer = ByteBuffer.allocateDirect(4 * timageSize * timageSize *3);
            tbyteBuffer.order(ByteOrder.nativeOrder());

            int[]tintValues = new int[timageSize * timageSize];
            image.getPixels(tintValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int tpixel = 0;
            for(int i = 0; i< timageSize; i++){
                for(int j = 0; j<timageSize; j++){
                    int tval = tintValues[tpixel++];
                    tbyteBuffer.putFloat(((tval >> 16) & 0xFF) * (1.f / 255.f));
                    tbyteBuffer.putFloat(((tval >> 8) & 0xFF) * (1.f / 255.f));
                    tbyteBuffer.putFloat((tval & 0xFF) * (1.f / 255.f));

                }
            }


            inputFeature0.loadBuffer(tbyteBuffer);

            ModelT.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] tconfidences = outputFeature0.getFloatArray();
            int tmaxPos = 0;
            float tmaxConfidence = 0;

            System.out.println(tconfidences);



            // Runs model inference and gets result.



            for(int i = 0; i < tconfidences.length; i++){
                if(tconfidences[i] > tmaxConfidence){
                    tmaxConfidence = tconfidences[i];
                    tmaxPos = i;

                }
                day_t = simpleDateFormat;
            }



            String result_t = tclasses[tmaxPos].trim();
            tresult = Float.toString(tconfidences[0]*100).trim();
            editor.putString("tresult", tresult);
            editor.putString("result_t", result_t);

            String tdate_text_t = new SimpleDateFormat("MM.dd", Locale.getDefault()).format(currentTime);
            String day_t = tdate_text_t;
            editor.putString("tday_t", day_t);

            System.out.println(tmaxPos + tclasses[tmaxPos] );
            System.out.println("큰 값은 " + tmaxConfidence * 100);


            if( tmaxConfidence * 100 < 90) {
                Toast.makeText(t4Activity.this, "정확도가 낮아요! 재촬영이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
            if( tmaxPos == 0 ) {
                //증상이 높을 경우 수의사 진단 필요함을 안내하는 'result_info' VO속 result_info에 저장하기.
                VO.setResult_info(tresult_info);
            }



            editor.apply();
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
        t3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> t = getStringArray("tjsonArray"); //측정 결과 받아온 arraylist
                ArrayList<String> tDay = getStringArray("tDayjsonArray");; //측정 날짜 받아온 arraylist


                if(tresult != null) {
                    t.add(tresult);
                    VO.settArray(t);

                    tDay.add(day_t);
                    VO.settDayArray(tDay);

                    setStringArray("tjsonArray",VO.gettArray()); //저장해둔 lArray를 SP에 저장하기.
                    setStringArray("tDayjsonArray",VO.gettDayArray()); //저장해둔 lDayArray를 SP에 저장하기.
                }


                //[TEST]====출력해보기 위해 다시 한번 가져와보기 test용
                t = getStringArray("tjsonArray"); //측정 결과 받아온 arraylist
                tDay = getStringArray("tDayjsonArray");; //측정 날짜 받아온 arraylist

                System.out.println("지금까지의 기록 결과 (측정 날짜)(왼쪽) : "+tDay+t);
                System.out.println("지금까지의 기록 결과 (측정 날짜)(오른쪽) : "+tDay+t);

                Intent intent3 = new Intent(t4Activity.this, t5Activity.class);
                startActivity(intent3);

            }

        });


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            t3pic.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, timageSize, timageSize, false);
            classifytimage(image);





        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    //Arraylist를 JSONArray로 변환해서 SP에 저장하는 함수
    private void setStringArray(String key, ArrayList<String> values) {
        //내부저장소 SP 사용
        SharedPreferences sharedPreferences = getSharedPreferences("t_total_result", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        JSONArray jsonArray = new JSONArray(); //저장할 JSONArray 생성

        for (int i = 0; i < values.size(); i++) { //입력받은 arraylist 값의 values를 돌면서 jsonArray에 넣기
            jsonArray.put(values.get(i));
        }

        if (!values.isEmpty()) { //만약 입력값(arraylist)이 비어있는게 아니라면, 변경한 jsonArray 값을 SP에 저장하기.
            editor.putString(key, jsonArray.toString()); //key 값은 왼쪽 오른쪽 날짜 배열 모두에서 돌려야하므로 각각 설정하도록 !
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    //SP에 저장된 jsonArray를 arraylist로 변경하는 함수
    private ArrayList getStringArray(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("t_total_result", Context.MODE_PRIVATE );
        String json = sharedPreferences.getString(key, ""); //가져올 key값
        ArrayList list = new ArrayList();

        if (json != null) { //key값에 값이 저장되어 있다면
            try {
                JSONArray a = new JSONArray(json); //for문 돌면서 list에 저장 ->Arraylist 만들기

                for (int i = 0; i < a.length(); i++) {
                    String d = a.optString(i);
                    list.add(d);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }



}
