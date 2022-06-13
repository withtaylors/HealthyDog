package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class c5Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c5);

        TextView classified = findViewById(R.id.classified); //눈 건강 체크 결과입니다 멘트
        TextView result_info = findViewById(R.id.result_info); //혼탁 증상률 높을 때 나오는 안내 문구
        ProgressBar progressBar_right = findViewById(R.id.progressBar_right); //오른쪽 프로그레스 바
        ProgressBar progressBar_left = findViewById(R.id.progressBar_left); //왼쪽 프로그레스 바
        TextView confidences_right = findViewById(R.id.confidences_right); //오른쪽 눈의 혼탁있을 확률 r_result
        TextView confidences_left = findViewById(R.id.confidences_left); //왼쪽 눈의 혼탁있을 확률 l_result
        TextView result_right = findViewById(R.id.result_right); //오른쪽 눈의 결과 멘트 result_r
        TextView result_left = findViewById(R.id.result_left); //왼쪽 눈의 결과 멘트 result_l
        ImageButton nextbutton = (ImageButton)findViewById(R.id.GoCare); //버튼 클릭 시 care로 이동
        TextView right = findViewById(R.id.right); //오른쪽 글씨
        TextView left = findViewById(R.id.left); //왼쪽 글씨

        //result_info 가져오기
        String sub_result_info = VO.getResult_info();
        result_info.setText(sub_result_info);

        //CheckOn 값 가져오기
        int CheckOn = VO.getCheckON();

        if(CheckOn == 1) { //왼쪽 눈 촬영만 클릭한 경우
            progressBar_right.setVisibility(View.GONE);
            confidences_right.setVisibility(View.GONE);
            result_right.setVisibility(View.GONE);
            right.setVisibility(View.GONE);
        } else if (CheckOn == 2){ //오른쪽 눈 촬영만 클릭한 경우
            progressBar_left.setVisibility(View.GONE);
            confidences_left.setVisibility(View.GONE);
            result_left.setVisibility(View.GONE);
            left.setVisibility(View.GONE);
        } else if (CheckOn == 3 || CheckOn == 4 ) { } //양쪽 눈 촬영 클릭한 경우 default

        //저장된 값 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("total_result", Context.MODE_PRIVATE );
        String result_r = sharedPreferences.getString("result_r", " "); //오른쪽 눈의 결과 멘트
        String result_l = sharedPreferences.getString("result_l", " "); //왼쪽 눈의 결과 멘트
        String r_result = sharedPreferences.getString("r_result", " "); //오른쪽 눈의 혼탁있을 확률
        String l_result = sharedPreferences.getString("l_result", " "); //왼쪽 눈의 혼탁있을 확률

        //사용자 반려동물 이름 값 출력하기
        sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE );
        String name = sharedPreferences.getString("name", " ");
        classified.setText(name+"의 눈 건강 체크 결과입니다.");
        //classified.setBackgroundColor(Color.parseColor("#FFC0CB"));

        //결과 값 출력하기
        confidences_right.setText(String.format("%.1f",Float.parseFloat(r_result)));
        confidences_left.setText(String.format("%.1f",Float.parseFloat(l_result)));
        result_right.setText("오른쪽 : "+ result_r +"고 판정되었습니다.");
        result_left.setText("왼쪽 : "+ result_l +"고 판정되었습니다.");

        //결과값을 프로그래스바에 나타내기 - 이때 프로그래스바는 int형만 가능해서 기존 소수점 * 10을 해서 가져옵니다.
        progressBar_right.setProgress((int)(Float.parseFloat(r_result)*10));
        progressBar_left.setProgress((int) (Float.parseFloat(l_result)*10));


        System.out.println(result_l);
        System.out.println(l_result);
        System.out.println(result_r);
        System.out.println(r_result);


        //버튼 클릭시 care로 이동하기
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int list = 2; //어디 페이지로 프레그먼트 시작할지 정하는 변수
                VO.setList(list); //VO 변수에 저장해두기
                Intent intent = new Intent(c5Activity.this, c1Activity.class);
                startActivity(intent);
                //야매로 c1은 액티비티니까 액티비티로 이동
                //대신 시작할 때 마이페이지 뜰 수 있게 list = 2로 변경
            }
        });

        // ----글씨 중간 속성 바꾸기 -------

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
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // spannableString.setSpan(new RelativeSizeSpan(1.3f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        function_text.setText(spannableString);

    }
}