package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my2, container,false);

        TextView name2 = view.findViewById(R.id.name2);
        TextView walk2 = view.findViewById(R.id.walk_2);

        //Bundle bundle1 = this.getArguments();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MY", Context.MODE_PRIVATE );
        String name = sharedPreferences.getString("name", "");
        String time = sharedPreferences.getString("time", "");
        name2.setText(name);
        walk2.setText(time);

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