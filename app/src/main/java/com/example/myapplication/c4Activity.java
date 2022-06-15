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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ml.Model;

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

public class c4Activity extends AppCompatActivity {
    ImageView imageView; //촬영사진
    ImageButton picture, button; //촬영버튼
    Button othereye, btn2; //측정버튼
    int imageSize = 224;

    String[] classes = {"혼탁 증상 확률이 높다", "혼탁 증상 확률이 낮다"};
    String result_info = "각막의 혼탁이 부분적으로 나타날 경우 지방이나 칼슘의 침착, 이전 상처에 대한 흉터일 가능성도 있어요. 전반적인 각막의 혼탁이 나타난다면 각막 부종이나 녹내장 등과 같은 질환일 수 있으니 동물병원에서 정확한 원인을 체크받길 추천해요."; //혼탁 증상 확률이 높을 경우 출력되는 '수의사 측정 요망' 문구

    int CheckOn ; //선택된 눈의 값. 왼쪽 체크 시 값 1, 오른쪽 체크 시 2, 둘 다 체크 시 3

    String r_result; //오른쪽 눈의 혼탁률
    String l_result; //왼쪽 눈의 혼탁률

    Date currentTime = Calendar.getInstance().getTime(); //현재 시간 가져오기
    String simpleDateFormat = new SimpleDateFormat("MM.dd", Locale.getDefault()).format(currentTime); //시간 저장 할 데이터포멧

    String day_r, day_l; //측정한 날짜

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c4);

        othereye = findViewById(R.id.othereye); //다른쪽 눈(오른쪽) 촬영하기 버튼
        button = findViewById(R.id.button); //카메라 버튼
        TextView textView2 = findViewById(R.id.textView2);
        btn2 = findViewById(R.id.button2); //측정하기 버튼

        //CheckOn 값 (촬영할 눈 선택 값) 가져오기
        CheckOn = VO.getCheckON();

        if(CheckOn == 1) { //왼쪽 눈 촬영만 클릭한 경우
            textView2.setText("왼쪽 눈을 촬영해주세요");
            button.setVisibility(View.VISIBLE);
        } else if (CheckOn == 2){ //오른쪽 눈 촬영만 클릭한 경우
            textView2.setText("오른쪽 눈을 촬영해주세요");
            button.setVisibility(View.VISIBLE);
        } else if (CheckOn == 3){ //양쪽 눈 촬영 클릭한 경우 - 왼쪽 먼저 실행
            textView2.setText("왼쪽 눈을 촬영해주세요");
            button.setVisibility(View.VISIBLE);
            othereye.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.GONE);
        } else if (CheckOn == 4) { //양쪽 눈 촬영 클릭한 경우 - 오른쪽 실행
            textView2.setText("오른쪽 눈을 촬영해주세요");
            button.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
        }//양쪽 눈 촬영 클릭한 경우 CheckOn 디폴트 값 '4' - 왼쪽 실행 후 오른쪽


        imageView = findViewById(R.id.imageView);
        picture = (ImageButton)findViewById(R.id.button);

        //앱 카메라 허용 시 사진 촬영 가능
        picture.setOnClickListener(new OnClickListener() {
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

    //사진 촬영 후 비트맵으로 이미지 띄우기
    public void classifyImage(Bitmap image){

        //내부저장소 SP 사용
        SharedPreferences sharedPreferences = getSharedPreferences("total_result", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            // get 1D array of 224 * 224 pixels in image
            int [] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            // iterate over pixels and extract R, G, and B values. Add to bytebuffer.
            int pixel = 0;
            for(int i = 0; i<imageSize; i++){
                for(int j=0; j<imageSize; j++){
                    int val = intValues[pixel++]; //RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result. Model 돌리기 및 결과 값 가져오기
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            //정확도 오른쪽, 왼쪽눈에 각각 저장 -----------------------
            float[] confidences_l = outputFeature0.getFloatArray();
            float[] confidences_r = outputFeature0.getFloatArray();

            int maxPos_l =0, maxPos_r = 0; //큰 번호 값 저장
            float maxConfidence_l = 0, maxConfidence_r = 0; //큰 정확률 값

            //배열에 잘 저장돠는지 확인하기 위해 일단 확인!
            System.out.println(confidences_l);
            System.out.println(confidences_r);

            if(CheckOn == 1 || CheckOn ==3){ //왼쪽눈 촬영시
                //큰 값 저장하기
                for(int i =0; i<confidences_l.length; i++){
                    if(confidences_l[i] >= maxConfidence_l){
                        maxConfidence_l = confidences_l[i];
                        maxPos_l = i;
                    }
                }

                day_l = simpleDateFormat; //왼쪽 눈 촬영 날짜 저장하기

                System.out.println(maxPos_l + classes[maxPos_l] );
                System.out.println("큰 값은 " + maxConfidence_l * 100);

                String result_l = classes[maxPos_l].trim();
                l_result = Float.toString(confidences_l[0]*100).trim();
                editor.putString("result_l", result_l);
                editor.putString("l_result", l_result);
                editor.putString("day_l", day_l);

                //정확도가 90% 미만일 경우 토스트 메시지 출력
                if( maxConfidence_l * 100 < 90) {
                    Toast.makeText(c4Activity.this, "정확도가 낮아요! 재촬영이 필요합니다.", Toast.LENGTH_SHORT).show();
                }

                //눈 혼탁 증상률이 높다고 판정될 경우, 전문 수의사의 진단이 필요함을 안내하는 문구
                if( maxPos_l == 0 ){
                    //증상이 높을 경우 수의사 진단 필요함을 안내하는 'result_info' VO속 result_info에 저장하기.
                    VO.setResult_infot(result_info);
                }
            }
            if (CheckOn == 2 || CheckOn == 4){ //오른쪽눈 촬영시
                //큰 값 저장하기
                for(int i =0; i<confidences_r.length; i++){
                    if(confidences_r[i] > maxConfidence_r){
                        maxConfidence_r = confidences_r[i];
                        maxPos_r = i;
                    }
                }

                day_r = simpleDateFormat; //오른쪽 눈 촬영 날짜 저장하기

                System.out.println(maxPos_r + classes[maxPos_r] );
                System.out.println("큰 값은 " + maxConfidence_r * 100);

                String result_r = classes[maxPos_r].trim();
                r_result = Float.toString(confidences_r[0]*100);
                editor.putString("result_r", result_r);
                editor.putString("r_result", r_result);
                editor.putString("day_r", day_r);

                //정확도가 90% 미만일 경우 토스트 메시지 출력
                if( maxConfidence_r * 100 < 90) {
                    Toast.makeText(c4Activity.this, "정확도가 낮아요! 재촬영이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
                //눈 혼탁 증상률이 높다고 판정될 경우, 전문 수의사의 진단이 필요함을 안내하는 문구
                if( maxPos_r == 0 ){
                    //증상이 높을 경우 수의사 진단 필요함을 안내하는 'result_info' VO속 result_info에 저장하기.
                    VO.setResult_infot(result_info);
                }
            }

            //결과 값 저장하기
            editor.apply();
            // Releases model resources if no longer used.
            model.close();

        } catch (IOException e) {
            //TODO Handle the exception
        }

        //오른쪽 눈 촬영하기 버튼 클릭했을 경우 해당 액티비티 다시 실행
        othereye.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //왼쪽눈 촬영 결과값 저장해두기
                ArrayList<String> l = getStringArray("ljsonArray"); //왼쪽 눈 측정 결과 받아온 arraylist
                ArrayList<String> lDay = getStringArray("lDayjsonArray");; //왼쪽 눈 측정 날짜 받아온 arraylist

                //ArrayList<String> l = VO.getlArray();
                l.add(l_result);
                VO.setlArray(l);

                //ArrayList<String> lDay = VO.getlDayArray();
                lDay.add(day_l);
                VO.setlDayArray(lDay);

                setStringArray("ljsonArray",VO.getlArray()); //저장해둔 lArray를 SP에 저장하기.
                setStringArray("lDayjsonArray",VO.getlDayArray()); //저장해둔 lDayArray를 SP에 저장하기.

                CheckOn = 4;
                VO.setCheckON(CheckOn);
                startActivity(new Intent(c4Activity.this,c4Activity.class));
            }
        });

        //측정하기 버튼 클릭했을 때 인텐트 c5 이동
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> r = getStringArray("rjsonArray"); //오른쪽 눈 측정 결과 받아온 arraylist
                ArrayList<String> rDay = getStringArray("rDayjsonArray");; //오른쪽 눈 측정 날짜 받아온 arraylist
                ArrayList<String> l = getStringArray("ljsonArray"); //왼쪽 눈 측정 결과 받아온 arraylist
                ArrayList<String> lDay = getStringArray("lDayjsonArray");; //왼쪽 눈 측정 날짜 받아온 arraylist

                //눈의 결과 값과 측정 날짜 저장해나갈 ArrayList
                if(VO.getCheckON() == 2 || VO.getCheckON() == 4) {
                    //ArrayList<String> r = VO.getrArray();
                    r.add(r_result);
                    VO.setrArray(r); //VO에 넘겨주기

                    //ArrayList<String> rDay = VO.getrDayArray();
                    rDay.add(day_r);
                    VO.setrDayArray(rDay); //VO에 넘겨주기

                    setStringArray("rjsonArray",VO.getrArray()); //저장해둔 rArray를 SP에 저장하기.
                    setStringArray("rDayjsonArray",VO.getrDayArray()); //저장해둔 rDayArray를 SP에 저장하기.
                }
                if(VO.getCheckON() == 1 ) { //왼쪽 눈 촬영일때만! 양쪽 촬영 후 오른쪽 눈으로 넘어가면, 새로 시작하므로 데이터 저장이 안된다. 그러므로 오른쪽눈 측정하기 버튼 눌렀을 때에 저장하도록 구현했음
                    //눈의 결과 값 저장해나갈 ArrayList
                    //ArrayList<String> l = VO.getlArray();

                    l.add(l_result);
                    VO.setlArray(l); //VO에 넘겨주기

                    //ArrayList<String> lDay = VO.getlDayArray();
                    lDay.add(day_l);
                    VO.setlDayArray(lDay); //VO에 넘겨주기

                    setStringArray("ljsonArray",VO.getlArray()); //저장해둔 lArray를 SP에 저장하기.
                    setStringArray("lDayjsonArray",VO.getlDayArray()); //저장해둔 lDayArray를 SP에 저장하기.
                }

                //[TEST]====출력해보기 위해 다시 한번 가져와보기 test용
                r = getStringArray("rjsonArray"); //오른쪽 눈 측정 결과 받아온 arraylist
                rDay = getStringArray("rDayjsonArray");; //오른쪽 눈 측정 날짜 받아온 arraylist
                l = getStringArray("ljsonArray"); //오른쪽 눈 측정 결과 받아온 arraylist
                lDay = getStringArray("lDayjsonArray");; //오른쪽 눈 측정 날짜 받아온 arraylist

                //System.out.println("지금까지의 기록 결과 (측정 날짜)(왼쪽) : "+VO.getlDayArray()+VO.getlArray());
                //System.out.println("지금까지의 기록 결과 (측정 날짜)(오른쪽) : "+VO.getrDayArray()+VO.getrArray());
                System.out.println("지금까지의 기록 결과 (측정 날짜)(왼쪽) : "+lDay+l);
                System.out.println("지금까지의 기록 결과 (측정 날짜)(오른쪽) : "+rDay+r);

                Intent intent = new Intent(c4Activity.this, c5Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode ==RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
            classifyImage(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Arraylist를 JSONArray로 변환해서 SP에 저장하는 함수
    private void setStringArray(String key, ArrayList<String> values) {
        //내부저장소 SP 사용
        SharedPreferences sharedPreferences = getSharedPreferences("total_result", Context.MODE_PRIVATE);
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
        SharedPreferences sharedPreferences = getSharedPreferences("total_result", Context.MODE_PRIVATE );
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