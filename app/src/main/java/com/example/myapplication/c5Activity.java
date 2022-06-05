package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class c5Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c5);
        TextView classified = findViewById(R.id.classified);
        TextView result_info = findViewById(R.id.result_info);
        ProgressBar progressBar_right = findViewById(R.id.progressBar_right);
        ProgressBar progressBar_left = findViewById(R.id.progressBar_left);

        //result_info 받아오기
        String sub_result_info ;
        Intent intent = getIntent();
        sub_result_info = intent.getStringExtra("result_info");

        //저장된 값 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("total_result", Context.MODE_PRIVATE );
        String result_l = sharedPreferences.getString("result_l", "");
        String result_r = sharedPreferences.getString("result_r", "");
        String l_result = sharedPreferences.getString("l_result", "");
        String r_result = sharedPreferences.getString("r_result", "");

        classified.setText("측정한 눈의 "+ result_l +"고 판정되었습니다.");


        //결과값을 프로그래스바에 나타내기
/*
        progressBar_left.setProgress(Integer.parseInt(l_result));
        progressBar_right.setProgress(Integer.parseInt(r_result));
*/

        System.out.println(result_l);
        System.out.println(l_result);
        System.out.println(result_r);
        System.out.println(r_result);


        //사용자 반려동물 이름 값 출력하기
        sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE );
        String name = sharedPreferences.getString("name", "");
        classified.setText(name+"의 눈 건강 체크 결과입니다.");

        //infoText 중간 글씨 색 바꾸기
        TextView function_text = (TextView)findViewById(R.id.infoText); //텍스트 변수 선언
        String content = function_text.getText().toString(); //텍스트 가져옴.
        SpannableString spannableString = new SpannableString(content); //객체 생성

        //infoText 속 특정 문자(눈 혼탁 증상이 있을 확률)의 시작위치 끝위치 얻기
        String word ="눈 혼탁 증상이 있을 확률";
        int start = content.indexOf(word);
        int end = start + word.length();

        //spannableString 속성 정하기
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#E77794")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // spannableString.setSpan(new RelativeSizeSpan(1.3f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        function_text.setText(spannableString);

    }
}