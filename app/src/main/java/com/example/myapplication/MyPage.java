package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyPage extends Fragment {
    private boolean selected;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my2, container,false);

        ImageView profile2 = view.findViewById(R.id.profile2);

        TextView name2 = view.findViewById(R.id.name2);
        TextView walk2 = view.findViewById(R.id.walk2);
        TextView birth2 = view.findViewById(R.id.birth_2);
        TextView type2 = view.findViewById(R.id.type2);
        TextView type3 = view.findViewById(R.id.type3);
        TextView typeinfo = view.findViewById(R.id.typeinfo);
        ImageView fatpic = view.findViewById(R.id.fatpic);
        TextView fatinfo = view.findViewById(R.id.fatinfo);
        ProgressBar progressBar = view.findViewById(R.id.progress);
        TextView gender2 = view.findViewById(R.id.gender2);



        /*fat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(selected == false){
                   selected = true;
                   fat1.setSelected(true);
                   fatinfo.setText("사료 양을 많이 늘려주세요!");
               }
               else{
                   selected =false;
                   fat1.setSelected(false);
               }
            }
        });*/

        int walktime;
        int howfat;




        //Bundle bundle1 = this.getArguments();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MY", Context.MODE_PRIVATE );
        String name = sharedPreferences.getString("name", " ");
        String str1 = sharedPreferences.getString("year", " ");
        String str2 = sharedPreferences.getString("month", " ");
        String str3 = sharedPreferences.getString("day", " ");
        String str4 = sharedPreferences.getString("type", " ");
        howfat= sharedPreferences.getInt("fat", 0);
        String gender = sharedPreferences.getString("gender", " ");
        int gen = sharedPreferences.getInt("gen",0);


        if(howfat == 0){

        }
        Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.fat1_2);
        if(howfat == 1){
            fatpic.setImageBitmap(icon);
            fatinfo.setText("사료의 양을 많이 늘려주세요!");
        }
        Bitmap icon2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.fat2_2);
        if(howfat == 2){
            fatpic.setImageBitmap(icon2);
            fatinfo.setText("사료의 양을 조금 더 늘려주세요!");
        }

        Bitmap icon3 = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.fat3_2);
        if(howfat == 3){
            fatpic.setImageBitmap(icon3);
            fatinfo.setText("건강한 체격이에요. 지금을 유지해주세요.");
        }
        Bitmap icon4 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.fat4_2);
        if(howfat == 4){
            fatpic.setImageBitmap(icon4);
            fatinfo.setText("사료의 양을 조금 줄이거나 운동을 시켜주세요.");
        }
        Bitmap icon5 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.fat5_2);
        if(howfat == 5){
            fatpic.setImageBitmap(icon5);
            fatinfo.setText("비만이에요. 전문가와 상담이 필요해요.");
        }

        //성별 선택값 가져옴
        System.out.print("====================================================="+gen);
        if(gen == 1) {
            gender2.setText("여아" + " " + gender);
        }else{
            gender2.setText("남아" +" " + gender);
        }

        if(str4.equals("견종")){
            typeinfo.setText(" ");
        }
        if(str4.equals("골든 리트리버")){
            typeinfo.setText("골든 리트리버는 총명하고 믿음직한 성격의 강아지입니다.\n 털이 이중모의 구조를 갖고 있어 봄에 속털이 빠지므로\n 이 시기에 솔질을 자주 해주어 털을 제거해야 합니다.\n 워낙 차분하기 때문에 운동을 자주 해주어\n 비만을 예방하는 것이 좋습니다." );

        }
        if(str4.equals("닥스훈트")){
            typeinfo.setText("닥스훈트는 독일어의 '오소리 사냥'이라는 뜻이 담겨있는 이름입니다.\n 이름에서처럼 오소리나 토끼를 추적하는데 활약했던 강아지이기에\n 겁이 없다는 특징을 갖고 있습니다.\n 몸이 길어 체중 조절과 운동에 신경을 써 주어야\n 척추 디스크를 예방할 수 있습니다. ");
        }
        if(str4.equals("래브라도 리트리버")){
            typeinfo.setText("래브라도 리트리버는 덩치가 매우 큰 대형견이지만 순박한 매력을 갖고 있습니다.\n 사람을 좋아하고 뛰어난 후각능력과 민첩성을 갖고 있어\n 안내견과 마약 및 폭탄 탐지견 등과 같은 목적견으로서 각광받고 있습니다.");
        }
        if(str4.equals("말티즈")){
            typeinfo.setText("말티즈는 활발하고 쾌할한 반려견으로 작은 몸에 비해 용감합니다.\n 목적견이나 사냥견의 역사가 없는 반려용 강아지로 유래가 깊습니다. 붙임성이 좋고 애교가 많아 사람을 잘 따르며\n 머리회전도 좋아 많은 재능을 발휘할 수 있습니다.");
        }
        if(str4.equals("믹스")){
            typeinfo.setText("두 개 이상의 견종이 섞인 믹스견은 부모와 같은 견종의 관리방법을 찾아 비슷한 케어를 해주어야 합니다.\n 한 쪽의 특성이 강하게 나타나는 경우와 양쪽의 특성이 반씩 나타나는 경우가 있습니다.");
        }
        if(str4.equals("보더콜리")){
            typeinfo.setText("보더콜리는 지능이 높고 끈기가 있으며 보호자에게 순종하는 기질을 갖고 있습니다.\n 목양견 중 최고로 뽑힐 정도로 활동적이기 때문에\n 넓은 마당이나 주택에서 키우는 것이 좋습니다.");
        }
        if(str4.equals("비숑 프리제")){
            typeinfo.setText("비숑 프리제는 보호자에 대한 깊은 애착을 가지며 다른 동물이나 어린이들과도 잘 어울립니다.\n 활동성도 평균 수준이므로 실내에서 기르기에 알맞는 반려견입니다.\n 털 빠짐이 적지만 가늘어서 잘 엉키기 때문에\n 자주 빗어주고 손질해주어야 합니다.");
        }
        if(str4.equals("시베리안 허스키")){
            typeinfo.setText("시베리아에서 썰매를 끌던 개로 단단한 근육과 균형 잡힌 몸매를 갖고 있습니다.\n 온순한 성격의 소유자이며 보호자에게 뛰어난 충성심을 보입니다.\n 추운 지방에 살던 견종이기 때문에 숱이 많은 털을 갖고 있어 털이 많이 빠지므로\n 빗질을 자주 해주고 더운 날 과도한 운동은 피하는 게 좋습니다.");
        }
        if(str4.equals("시츄")){
            typeinfo.setText("납작한 코가 매력인 시츄는 온후한 성격을 갖고 있어 사람을 매우 좋아합니다.\n 움직이는 것을 좋아하므로 운동을 자주 시키는 것이 좋습니다.");
        }
        if(str4.equals("요크셔테리어")){
            typeinfo.setText("작고 귀여운 얼굴과 반짝이는 긴 털을 갖고 있는 요크셔테리어는 똑똑하고 보호자를 잘 따릅니다.\n 보호자에 대한 소유욕이 강한 편이므로 교육을 해주어 적절한 애착 관계를 성립해야 합니다.");
        }
        if(str4.equals("진돗개")){
            typeinfo.setText("진돗개는 대한민국에서 천연기념물로 지정한 견종으로 본래는 사냥견의 유래를 갖고 있습니다.\n낯선 사람이나 동물에 대해 경계하는 편이므로 어릴 때부터 사회화 훈련을 하는 것이 좋습니다.");
        }
        if(str4.equals("치와와")){
            typeinfo.setText("치와와는 달리고 노는 것을 좋아하여 산책을 자주 해주어야 합니다.\n 장모종과 단모종으로 나뉘며 장모종의 경우,털을 자주 빗어주어야 합니다.\n");
        }
        if(str4.equals("코커 스파니엘")){
            typeinfo.setText("코커 스파니엘은 귀여운 외모와 활발한 성격으로 많은 애견인들의 사랑을 받고 있습니다.\n 온순하고 다정한 성격이며 활동량이 많아 마당이 있는 주택에서 키우는 것이 좋습니다.");
        }
        if(str4.equals("포메라니안")){
            typeinfo.setText("포메라니안은 북극에서 썰매를 끌던 개의 후손입니다.\n 공처럼 둥글고 풍성하게 부풀어 오른 털이 특징이며\n 영리하고 작은 체구에 비해 대범한 성격을 가지고 있ㅇ으며 호기심이 많습니다.\n 짖음이 많은 편이므로 일찍 적절한 훈련을 해주어야 합니다.");
        }
        if(str4.equals("푸들")){
            typeinfo.setText("영리하고 애교가 많아 사랑을 받고 있는 견종입니다.\n 양처럼 곱슬곱슬하고 촘촘한 털을 갖고 있어 털이 잘 빠지지 않아\n 털 관리가 비교적 쉬운 견종입니다.");
        }
        if(str4.equals("기타")){
            typeinfo.setText("자신의 반려동물의 견종을 아는 것은 매우 중요합니다.\n 반려동물과 행복한 공존 생활을 하기 위해 반려동물의 견종을 알고\n 그에 따른 다양한 특징을 아는 것이 좋습니다.");
        }
        //image string to bitmap
        String temp1 = sharedPreferences.getString("profile", " ");
        byte[] encodeByte = Base64.decode(temp1, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        profile2.setImageBitmap(bitmap);





        birth2.setText(str1+" . "+str2+" . "+str3);
        type2.setText(str4);
        type3.setText(str4);


        walktime = sharedPreferences.getInt("time", 0);
        progressBar.setProgress(walktime);
        if(walktime < 30){
            walk2.setText(String.valueOf(walktime)+"분은 너무 적어요. 산책시간을 늘려주세요.");

        }
        else if(30 <= walktime && walktime <100 ){
            walk2.setText(String.valueOf(walktime)+"분 산책했어요. 딱 적당해요.");
        }
        else
            walk2.setText(String.valueOf(walktime)+"분 산책은 강아지가 힘들 수 있어요. 컨디션을 잘 조절해주세요.");

        name2.setText(name);

/*        if(bundle1 != null) {
            bundle1 = getArguments();

            String name = bundle1.getString("name");
            name2.setText(name);
        }*/


        Button changeinfo = (Button)view.findViewById(R.id.changeinfo);
        if ( name == " " ) {
            changeinfo.setText("반려동물 정보 입력하기");
        }



        changeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(getActivity(), my1.class);
                startActivity(intent3);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


}