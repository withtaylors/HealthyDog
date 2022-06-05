package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my2, container,false);

        TextView name2 = view.findViewById(R.id.name2);
        TextView walk2 = view.findViewById(R.id.walk2);
        TextView birth2 = view.findViewById(R.id.birth_2);
        TextView type2 = view.findViewById(R.id.type2);
        TextView type3 = view.findViewById(R.id.type3);
        TextView fatinfo = view.findViewById(R.id.fatinfo);
        ImageButton fat1 = view.findViewById(R.id.fat1);
        ImageButton fat2 = view.findViewById(R.id.fat2);
        ProgressBar progressBar = view.findViewById(R.id.progress);




        fat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fatinfo.setText("사료 양을 많이 늘려주세요!");
            }
        });
        fat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fatinfo.setText("사료 양을 조금 늘려주세요!");
            }
        });

        int walktime;




        //Bundle bundle1 = this.getArguments();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MY", Context.MODE_PRIVATE );
        String name = sharedPreferences.getString("name", "");
        String str1 = sharedPreferences.getString("year", "");
        String str2 = sharedPreferences.getString("month", "");
        String str3 = sharedPreferences.getString("day", "");
        String str4 = sharedPreferences.getString("type", "");
        walktime = sharedPreferences.getInt("time", 0);
        progressBar.setProgress(walktime);











        birth2.setText(str1+" . "+str2+" . "+str3);
        type2.setText(str4);
        type3.setText(str4);
        walk2.setText(String.valueOf(walktime));




        name2.setText(name);

/*        if(bundle1 != null) {
            bundle1 = getArguments();

            String name = bundle1.getString("name");
            name2.setText(name);
        }*/


        Button changeinfo = (Button)view.findViewById(R.id.changeinfo);
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