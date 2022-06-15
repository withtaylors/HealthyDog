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
    String[] tclasses = {"치석이 있을 확률이 높다.", "치석이 있을 확률이 낮다."};



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



        t3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences tsharedPreferences = getSharedPreferences("t_total_result", Context.MODE_PRIVATE );
                tresult = tsharedPreferences.getString("tresult", " ");

                ArrayList<String> t = VO.gettArray();
                t.add(tresult);
                VO.settArray(t);

                System.out.println("============="+tresult);

                System.out.println("지금까지의 기록 결과 teeth : "+VO.gettArray());

                Intent intent3 = new Intent(t4Activity.this, t5Activity.class);
                startActivity(intent3);

            }

        });
    }
    public void classifytimage(Bitmap image){
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

            SharedPreferences sharedPreferences = getSharedPreferences("t_total_result", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Date currentTime = Calendar.getInstance().getTime();

            // Runs model inference and gets result.
            ModelT.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] tconfidences = outputFeature0.getFloatArray();
            int tmaxPos = 0;
            float tmaxConfidence = 0;
            for(int i = 0; i < tconfidences.length; i++){
                if(tconfidences[i] > tmaxConfidence){
                    tmaxConfidence = tconfidences[i];
                    tmaxPos = i;

                }
            }

            tresult = Float.toString(tconfidences[0]*100).trim();
            editor.putString("tresult", tresult);

            String tdate_text_l = new SimpleDateFormat("%y.MM.dd", Locale.getDefault()).format(currentTime);
            String tday_l = tdate_text_l;
            editor.putString("day_l", tday_l);

            String s = "";
            for(int i =0; i<tconfidences.length; i++){
                if(tconfidences[i] > tmaxConfidence){
                    tmaxConfidence = tconfidences[i];
                    tmaxPos = i;
                }

            }
            if( tmaxConfidence * 100 < 90) {
                Toast.makeText(t4Activity.this, "정확도가 낮아요! 재촬영이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
            System.out.println(tmaxPos + tclasses[tmaxPos] );
            System.out.println("큰 값은 " + tmaxConfidence * 100);




            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }

        SharedPreferences sharedPreferences = getSharedPreferences("t_total_result", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Date currentTime = Calendar.getInstance().getTime();

        String tdate_text_l = new SimpleDateFormat("%y.MM.dd", Locale.getDefault()).format(currentTime);
        String tday_l = tdate_text_l;
        editor.putString("day_l", tday_l);
        editor.apply();

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




}
