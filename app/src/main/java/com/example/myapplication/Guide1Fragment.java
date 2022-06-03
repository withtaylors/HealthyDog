package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
public class Guide1Fragment extends Fragment {
//
//    private String title;
//    public Guide1Fragment() {
//        this.title = title;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_guide1, container, false);

//        TextView text1 = view.findViewById(R.id.text1);
//        TextView text2 = view.findViewById(R.id.text2);
//        ImageView imageView = view.findViewById(R.id.imageView);
//
//        text1.setText(title);
//        text2.setText(title);

        return view;
    }
}