package com.example.myapplication;

import android.content.Intent;
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
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            bundle = getArguments();

            String name = bundle.getString("name");
            name2.setText(name);
        }

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