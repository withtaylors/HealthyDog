package com.example.myapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my2, container,false);
        TextView name2 = view.findViewById(R.id.name2);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MY", MODE_PRIVATE);
        String name = sharedPreferences.getString("value", "");
        name2.setText(name);
        // Inflate the layout for this fragment
        return view;
    }
}