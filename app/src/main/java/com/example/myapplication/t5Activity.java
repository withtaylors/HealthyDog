package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class t5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t5);

        TextView confidences_teeth = findViewById(R.id.confidences_teeth);
        TextView result_teeth = findViewById(R.id.result_teeth);
        TextView tresult_info = findViewById(R.id.tresult_info);
        ProgressBar progressBar_teeth = findViewById(R.id.progressBar_teeth);
        TextView tclassified = findViewById(R.id.tclassified);
        ImageButton tnextbutton = (ImageButton)findViewById(R.id.GoCare);
        TextView tfunction_text = (TextView)findViewById(R.id.tinfoText);







        SharedPreferences sharedPreferences = getSharedPreferences("t_total_result", Context.MODE_PRIVATE );
        String tresult = sharedPreferences.getString("tresult", " ");
        String result_t = sharedPreferences.getString("result_t", " ");
        String tsub_result_info = VO.getResult_info();
        tresult_info.setText(tsub_result_info);



        sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE );
        String name = sharedPreferences.getString("name", " ");
        tclassified.setText(name+"의 치아 건강 체크 결과입니다.");


        if(tresult != " "){
            confidences_teeth.setText(String.format("%.1f",Float.parseFloat(tresult)));
            progressBar_teeth.setProgress((int)(Float.parseFloat(tresult)*10));
            result_teeth.setText("오른쪽 : "+ result_t +"고 판정되었습니다.");
        }
        System.out.println(result_t);














       tnextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int list = 2; //어디 페이지로 프레그먼트 시작할지 정하는 변수
                VO.setList(list); //VO 변수에 저장해두기
                Intent intent4 = new Intent(t5Activity.this, c1Activity.class);
                startActivity(intent4);
                //야매로 c1은 액티비티니까 액티비티로 이동
                //대신 시작할 때 마이페이지 뜰 수 있게 list = 2로 변경
            }
        });


        /*TextView tfunction_text = (TextView)findViewById(R.id.tinfoText); //텍스트 변수 선언
        String content = tfunction_text.getText().toString(); //텍스트 가져옴.
        SpannableString spannableString = new SpannableString(content);


        String word ="눈 혼탁 증상이 있을 확률";
        int start = content.indexOf(word);
        int end = start + word.length();


        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#E77794")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tfunction_text.setText(spannableString);*/



    }
}