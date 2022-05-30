package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class c3Activity extends Activity {

    ImageButton btn2_change;
    ImageButton btn3_change;
    private boolean check2;
    private boolean check3;
    Button choose_button;

    int CheckON; //왼쪽 체크 시 값 1, 오른쪽 체크 시 2, 둘 다 체크 시 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_c3);

        Intent intent = new Intent(this, c4Activity.class);

        btn2_change = (ImageButton)findViewById(R.id.choose_eye_left);
        btn2_change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check2 == false){
                    check2 = true;
                    btn2_change.setSelected(true);
                }else {
                    check2 = false;
                    btn2_change.setSelected(false);
                }
                if (check2 == true || check3 == true ){
                    choose_button.setEnabled(true);
                }else{
                    choose_button.setEnabled(false);
                }

                //촬영할 눈 선택했을 때 그 값 전달
                //check2(왼쪽)만 눌렀을 경우 CheckOn = 1 전달, check3(오른쪽)만 눌렀을 경우 CheckOn = 2 전달, 둘 다 눌렀을 경우 CheckOn = 3 전달
                if(check2 == true || check3 == true ){
                    if(check3 == false){ //오른쪽이 안눌렸다 -> 즉 왼쪽만 눌렸다
                        CheckON = 1;
                        System.out.println("--------------------------왼쪽 눌렸어요");
                    }
                    else if(check2 == false){ // 왼쪽이 안눌렸다 -> 즉 오른쪽만 눌렸다
                        CheckON = 2;
                        System.out.println("--------------------------오른쪽 눌렸어요");
                    }
                    else{ //두가지 상황이 다 아니다 -> 즉 둘 다 눌렸다.
                        CheckON = 3;
                        System.out.println("--------------------------둘다 눌렸어요");
                    }
                }
            }
        });

        btn3_change = (ImageButton)findViewById(R.id.choose_eye_right);
        btn3_change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check3 == false){
                    check3 = true;
                    btn3_change.setSelected(true);
                }else {
                    check3 = false;
                    btn3_change.setSelected(false);
                }
                if (check2 == true || check3 == true ){
                    choose_button.setEnabled(true);
                }else{
                    choose_button.setEnabled(false);
                }

                //촬영할 눈 선택했을 때 그 값 전달
                //check2(왼쪽)만 눌렀을 경우 CheckOn = 1 전달, check3(오른쪽)만 눌렀을 경우 CheckOn = 2 전달, 둘 다 눌렀을 경우 CheckOn = 3 전달
                if(check2 == true || check3 == true ){
                    if(check3 == false){ //오른쪽이 안눌렸다 -> 즉 왼쪽만 눌렸다
                        CheckON = 1;
                        System.out.println("--------------------------왼쪽 눌렸어요");
                    }
                    else if(check2 == false){ // 왼쪽이 안눌렸다 -> 즉 오른쪽만 눌렸다
                        CheckON = 2;
                        System.out.println("--------------------------오른쪽 눌렸어요");
                    }
                    else{ //두가지 상황이 다 아니다 -> 즉 둘 다 눌렸다.
                        CheckON = 3;
                        System.out.println("--------------------------둘다 눌렸어요");
                    }
                }
            }
        });

        //촬영하기 버튼 클릭하면 CheckOn 인텐트 전달
        choose_button = (Button)findViewById(R.id.choose_button);
        choose_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("CheckOn",CheckON);
                startActivity(intent);
                finish();
            }
        });
    }
}